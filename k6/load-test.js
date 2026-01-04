import http from 'k6/http';
import { check, sleep } from 'k6';
import { Rate, Trend } from 'k6/metrics';

// Custom metrics
export const errorRate = new Rate('errors');
export const latency = new Trend('latency');

export const options = {
  stages: [
    { duration: '30s', target: 50 },   // ramp-up
    { duration: '1m', target: 200 },   // steady load
    { duration: '30s', target: 0 },    // ramp-down
  ],
  thresholds: {
    errors: ['rate<0.01'],            // error rate < 1%
    latency: ['p(95)<500'],           // 95th percentile latency < 500ms
  },
};

export default function () {
  const payload = JSON.stringify({
    fromAccountId: crypto.randomUUID(),
    toAccountId: crypto.randomUUID(),
    amount: Math.floor(Math.random() * 1000) + 1, // random amount between 1 and 1000
  });

  const params = {
    headers: {
      'Content-Type': 'application/json',
    },
  };

  const start = Date.now();

  const res = http.post(
    'http://payment-processor:8080/payments',
    payload,
    params
  );

  latency.add(Date.now() - start);

  const success = check(res, {
    'status is 202': (r) => r.status === 202,
  });

  errorRate.add(!success);

  sleep(0.1); // 100ms delay between iterations
}
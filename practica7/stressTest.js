import http from 'k6/http';
import { check } from 'k6';

export const options = {
    stages: [
        { duration: '3m', target: 48000 },
        { duration: '3m', target: 48000 },
        { duration: '2m', target: 0 },
    ],
    thresholds: {
        http_req_failed: ['rate<0.01'], // menos del 1% de peticiones fallidas
        http_req_duration: ['avg<1000'], // duración promedio < 1000ms
    },
};

export default function () {
    const res = http.get('http://localhost:8080/medico/1');
    check(res, {
        'status is 200': (r) => r.status === 200,
    });
}
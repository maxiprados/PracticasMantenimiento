import http from 'k6/http';
import { check, fail } from 'k6';

export const options = {
    stages: [
        { duration: '2m', target: 24000 }, // subida brusca hasta 40%
        { duration: '1m', target: 0 },     // bajada rápida para liberar recursos
    ],
    thresholds: {
        http_req_failed: ['rate<0.005'],  // menos del 0.5% de fallos
    },
};

export default function () {
    const res = http.get('http://localhost:8080/medico/1');
    const success = check(res, {
        'status is 200': (r) => r.status === 200,
    });

}

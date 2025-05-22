import http from 'k6/http';
import { check, sleep, fail } from 'k6';

export const options = {
    stages: [
        { duration: '3m', target: 1303 }, // subida a 50% carga
        { duration: '3m', target: 1303 }, // mantenimiento
        { duration: '2m', target: 0 },     // bajada
    ],
    thresholds: {
        http_req_failed: [
            { threshold: 'rate<0.01', abortOnFail: true }]  // menos del 1% de peticiones fallidas
    },
};

export default function () {
    const res = http.get('http://localhost:8080/medico/1');
    const success = check(res, {
        'status is 200': (r) => r.status === 200,
    });
    sleep(1);
}

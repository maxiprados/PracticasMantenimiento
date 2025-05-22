import http from 'k6/http';
import { check } from 'k6';

export let options = {
    thresholds: {
        http_req_failed: [
            { threshold: 'rate<0.01', abortOnFail: true }
        ],
    },
    stages: [
        { duration: '10m', target: 100000 },
       
    ],
};

export default function () {
    const res = http.get('http://localhost:8080/medico/1');
    check(res, {
        "response code was 200": (r) => r.status == 200,
    });
}
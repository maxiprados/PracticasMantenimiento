import { browser } from 'k6/browser';
import { check } from 'https://jslib.k6.io/k6-utils/1.5.0/index.js';

export const options = {
  scenarios: {
    ui: {
      executor: 'shared-iterations',
      options: { browser: { type: 'chromium' } },
      vus: 1,
      iterations: 1,
    },
  },
};

export default async function () {
  const page = await browser.newPage();
  await page.goto('http://localhost:4200');

  await page.locator('input[name="nombre"]').type('Carmen Machi');
  await page.locator('input[name="DNI"]').type('12458965Z');
  await page.locator('button[name="login"]').click();

  // Espera y verifica redirección al dashboard
  await page.waitForNavigation();
  await check(page.locator('h2'), {
    header: async (lo) => (await lo.textContent()) == 'Listado de pacientes',
  });
  await page.close();
}

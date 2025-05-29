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

  
  await page.waitForNavigation();
  await check(page.locator('h2'), {
    header: async (lo) => (await lo.textContent()) == 'Listado de pacientes',
  });

  await page.locator('button[name="add"]').click();
  await page.waitForNavigation();

  
  await page.locator('input[name="dni"]').type('1111111Z');
  await page.locator('input[name="nombre"]').type('Luisma');
  await page.locator('input[name="edad"]').type('38');
  await page.locator('input[name="cita"]').type('14-06-2025');
  await page.locator('button[name="login"]').click();

  await page.locator('button[type="submit"]').click();
  await page.waitForNavigation();

  await check(page.locator('table'), {
    header: async (lo) => (await parseInt(lo.$$("table tbody tr")[len1].$('td[nombre="Nombre"]').textContent())) == "Luisma"
  });





  await page.close();
}

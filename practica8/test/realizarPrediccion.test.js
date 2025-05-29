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

  // Esperar a que aparezca el listado
  await page.waitForSelector('td[name="nombre"]');

  // Usar evaluate para forzar click en la primera celda
  await page.evaluate(() => {
    const celdas = document.querySelectorAll('td[name="nombre"]');
    if (celdas.length > 0) {
      const evt = new MouseEvent('click', { bubbles: true, cancelable: true });
      celdas[0].dispatchEvent(evt);
    }
  });

  await page.locator('button[name="view"]').click();

  await page.locator('button[name="add"]').click();

  await page.locator('button.predict-button').click();
  await page.locator('textarea[matinput]').type('Paciente con sospecha, se recomienda revisión.');
  await page.locator('button[name="save"]').click();

  check(page, {
  'Informe guardado correctamente': async () =>
    (await page.content()).includes('Informe de la imagen'),
  });

  await page.close();
}

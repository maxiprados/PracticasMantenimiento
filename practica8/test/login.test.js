import { browser } from 'k6/experimental/browser';
import { check } from 'k6';

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
  const page = browser.newPage();
  await page.goto('http://localhost:4200');

  await page.locator('input[name="email"]').type('medico@hospital.com');
  await page.locator('input[name="password"]').type('1234');
  await page.locator('button[type="submit"]').click();

  // Espera y verifica redirección al dashboard
  await page.waitForNavigation();
  check(page, {
    'Redirige al dashboard': () => page.url().includes('/dashboard'),
  });

  await page.close();
}

/** @type {import('tailwindcss').Config} */
const { createGlobPatternsForDependencies } = require('@nx/angular/tailwind');
const { join } = require('path');

/** @type {import('tailwindcss').Config} */
module.exports = {
  prefix: '',
  mode: 'jit',
  darkMode: true,
  content: [
    join(__dirname, 'src/**/!(*.stories|*.spec).{ts,html}'),
    ...createGlobPatternsForDependencies(__dirname),
  ],
  theme: {
    screens: {
      sm: '640px',
      md: '768px',
      lg: '1024px',
      xl: '1280px',
      xxl: '1080px',
    },
    extend: {
      fontFamily: {
        lora: ['Lora', 'serif'],
      },
    },
    variants: {
      extend: {},
    },
    plugins: [],
  },
};

import { beep, playSequence } from './sounds/sound.js';
import { startBlinking, stopBlinking } from './blinking/blinking.js';

const display = document.getElementById('display');
const questionEl = document.getElementById('question');
const buttons = document.querySelectorAll('button');

let currentAnswer = '';
let started = false;
let maxDisplayChars = 8;
let mode = '';


/**
 * Verifica se o texto atual permite a adição de mais caracteres, baseado no limite do display.
 * 
 * @param {string} text - O texto a ser validado.
 * @returns {boolean} True se o tamanho do texto for menor que o limite máximo.
 */
function canAddCharacter(text) {
  return text.length < maxDisplayChars;
}


/**
 * Anima o texto no display principal, exibindo-o caractere por caractere.
 * 
 * @param {string} text - O texto a ser exibido no display de forma animada.
 * @returns {void}
 */
function animateDisplay(text) {
  display.innerText = "----";
  let i = 0;
  const interval = setInterval(() => {
    display.innerText = text.substring(0, i + 1);
    i++;
    if (i >= text.length) clearInterval(interval);
  }, 80);
}

function handleDesl() {
  started = false;
  animateDisplay("by by... ");
  display.innerText = "";
  questionEl.innerText = "Desligado";
}

function handleAdicao() {
  questionEl.innerText = "Pressione ENTER para começar";
  currentAnswer = "";
  display.innerText = "30+4=   ";
  startBlinking();
  maxDisplayChars = 2;
  started = true;
}

function handleLiga() {
  questionEl.innerText = "Pressione ENTER para começar";
  display.innerText = "  *    ";
}

function handleLivro() {
  questionEl.innerText = "Informe o numero do livro";
  display.innerText = "-";
  mode = 'livro';
  currentAnswer = "";
  started = true;
  maxDisplayChars = 3;
  startBlinking();
}

function handleEnter() {
  currentAnswer = "";
  maxDisplayChars = 8;
  started = false;
}

function handleInput(value) {
  if (!isNaN(value) || ['+', '-', 'X', '÷'].includes(value)) {
    let displaySymbol = value;
    // Mapeia X e ÷ para * e / como solicitado
    if (value === 'X') displaySymbol = '*';
    if (value === '÷') displaySymbol = '/';

    if (currentAnswer.length >= maxDisplayChars) {
      currentAnswer = currentAnswer.substring(1) + displaySymbol;
    } else {
      currentAnswer += displaySymbol;
    }
    display.innerText = currentAnswer;
  }
}

buttons.forEach(btn => {
  btn.addEventListener('click', () => {
    const value = btn.innerText.split("\n")[0];
    beep();
    stopBlinking();

    if (value === 'DESL') {
      handleDesl();
      return;
    }

    if (value === 'ADIÇÃO') {
      handleAdicao();
      return;
    }

    if (value === 'LIGA') {
      handleLiga();
      return;
    }

    if (value === 'LIVRO') {
      handleLivro();
      return;
    }

    if (value === 'ENTER') {
      handleEnter();
      return;
    }

    if (!started) return;

    handleInput(value);
  });
});






let blinkInterval = null;
let blinkText = '';

/**
 * Inicia o piscar do texto do display a cada 2 segundos, mantendo a tela ligada.
 * 
 * @returns {void}
 */
export function startBlinking() {
    stopBlinking();
    blinkText = display.innerText;
    let isVisible = true;
    blinkInterval = setInterval(() => {
        isVisible = !isVisible;
        display.innerText = isVisible ? blinkText : "";
    }, 500);
}

/**
 * Para o efeito de piscar e restaura o texto do display se necessário.
 * 
 * @returns {void}
 */
export function stopBlinking() {
    if (blinkInterval) {
        clearInterval(blinkInterval);
        blinkInterval = null;
        if (blinkText) {
            display.innerText = blinkText;
        }
    }
}
/**
 * Emite um som de bipe com a frequência e duração especificadas.
 * Utiliza a API de Web Audio para gerar o som.
 * 
 * @param {number} [freq=600] - A frequência do som em Hertz (Hz).
 * @param {number} [duration=100] - A duração do som em milissegundos (ms).
 * @returns {void}
 */
export function beep(freq = 600, duration = 100) {
    const ctx = new (window.AudioContext || window.webkitAudioContext)();
    const osc = ctx.createOscillator();
    const gain = ctx.createGain();
    osc.connect(gain);
    gain.connect(ctx.destination);
    osc.frequency.value = freq;
    osc.type = 'square';
    osc.start();
    gain.gain.setValueAtTime(0.1, ctx.currentTime);
    setTimeout(() => osc.stop(), duration);
}

const ctx = new (window.AudioContext || window.webkitAudioContext)();
const somPenseBem = [
    { freq: 523, duration: 0.12 }, // dó
    { freq: 659, duration: 0.12 }, // mi
    { freq: 783, duration: 0.12 }, // sol
    { freq: 1046, duration: 0.2 }, // dó agudo
];

const beepUnico = [{ freq: 600, duration: 1 }]

const notes = [
    { freq: 261, duration: 0.1 },   // DO
    { freq: 293, duration: 0.1 },   // RE
    { freq: 329, duration: 0.1 },  // MI
    { freq: 349, duration: 0.1 },  // FA
    { freq: 392, duration: 0.1 },  // SOL
    { freq: 440, duration: 0.1 },  // LA
    { freq: 493, duration: 0.1 },  // SI
    { freq: 523, duration: 0.1 },  // DO2
    { freq: 587, duration: 0.1 },  // RE2
    { freq: 659, duration: 0.1 }  // MI2
];

const sequenciaNotas = [
    { freq: 92.50, duration: 0.15 },   // F#2
    { freq: 98.00, duration: 0.15 },   // G2
    { freq: 110.00, duration: 0.15 },  // A2
    { freq: 146.83, duration: 0.15 },  // D3
    { freq: 155.56, duration: 0.15 },  // D#3
    { freq: 174.61, duration: 0.15 },  // F3
    { freq: 185.00, duration: 0.15 },  // F#3
    { freq: 220.00, duration: 0.15 },  // A3
    { freq: 277.18, duration: 0.15 },  // C#4
    { freq: 311.13, duration: 0.15 },  // D#4
    { freq: 698.46, duration: 0.18 },  // F5
    { freq: 1046.50, duration: 0.18 }, // C6
    { freq: 1244.51, duration: 0.22 }  // D#6
];


/**
 * Reproduz uma sequência de sons (notas musicais) baseada nas frequências e durações fornecidas.
 * 
 * @param {Array<{freq: number, duration: number, pause?: number}>} sequence - Um array de objetos, onde cada objeto representa uma nota com sua frequência em Hertz (`freq`), duração em segundos (`duration`), e opcionalmente um tempo de pausa (`pause`).
 * @returns {void}
 */
export function playSequence(sequence) {
    let time = ctx.currentTime;

    sequence.forEach(note => {
        const osc = ctx.createOscillator();
        const gain = ctx.createGain();

        osc.type = "square";
        osc.frequency.value = note.freq;

        osc.connect(gain);
        gain.connect(ctx.destination);

        gain.gain.setValueAtTime(0.2, time);
        gain.gain.exponentialRampToValueAtTime(0.001, time + note.duration);

        osc.start(time);
        osc.stop(time + note.duration);

        time += note.duration + (note.pause || 0);
    });
}
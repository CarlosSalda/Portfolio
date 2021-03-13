const app = Vue.createApp({
  data() {
    return {
      title: "Calculadora Vue",
      numero1: 0,
      numero2: 0,
    };
  },
  computed: {
    suma() {
      return this.numero1 + this.numero2;
    },
    resta() {
      return this.numero1 - this.numero2;
    },
    multiplicar() {
      return this.numero1 * this.numero2;
    },
    dividir() {
      const res = this.numero1 / this.numero2;

        console.log(res);

      return res === Infinity || res === -Infinity || res === NaN
        ? "Error- No calculable"
        : res;
    },
  },
});

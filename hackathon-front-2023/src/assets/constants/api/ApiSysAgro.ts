export default class APISysAgro {
    // Os links de get e post são iguais na nossa API.
    // Acima dos links, tem se tem GET ou POST disponíveis.
    // http://10.0.2.2:8080/sysagro/rs
    // private static defaultServer = "http://192.168.1.5:8080/sysagro/rs";
    // private static servidor = "http://192.168.1.5:8080/sysagro/rs";
    private static defaultServer = "http://localhost:8080/sysagro/rs";
    private static servidor = "http://localhost:8080/sysagro/rs";
  
    static getServidor() {
      return this.servidor;
    }
  
    static setServidor(servidor: string) {
      this.servidor = servidor;
    }
  
    static resetServidor() {
      this.servidor = this.defaultServer;
    }
  }
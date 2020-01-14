class Exercise {

    constructor(name, duration, description){
        this.name = name;
        this.duration = duration | 30;
        this.description = description ? description : "";
    }

    toString() {
        return `${this.name} for ${this.duration}s`
    }
}

export default Exercise;
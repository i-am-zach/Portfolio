import Exercise from './Exercise';

class Workout{

    constructor(exercises){
        this.exercises = exercises ? exercises : [];
        this.index = 0;
    }

    add(exercise){
        this.exercises = [...this.exercises, exercise]
    }

    add(position, exercise){
        this.exercises.insert(position, exercise);
    }

    nextExercise(){
        return this.index + 1 < this.exercises.length ? this.exercises[++this.index] : null;
    }

    previousExercise(){
        return this.index - 1 >= 0 ? this.exercises[--this.index] : null;
    }

    remove(exercise) {
        this.exercises.splice(exercise, 1);
    }

    get() {
        return this.exercises[this.index];
    }

    hasNext() {
        return this.index + 1 < this.exercises.length;
    }

    hasPrevious() {
        return this.index - 1 >= 0;
    }
}

export default Workout;
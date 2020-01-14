class Stack {

    constructor(){
        this.size = 0;
        this.stack = [];
    }

    size(){
        return this.size;
    }

    empty(){
        return this.size == 0;
    }

    peek(){
        if(empty()){
            return undefined;
        }
        return this.stack[this.size-1];
    }

    pop(){
        if(this.size == 0){
            return undefined;
        }
        this.size--;
        let item = this.stack[this.size];
        let temp = [];
        for(let i = 0; i < this.size; i++){
            temp[i] = this.stack[i];
        }
        this.stack = temp;
        return item;
    }

    push(item){
        this.stack[this.size] = item;
        this.size++;
    }

    clear(){
        this.size = 0;
        this.stack = [];
    }

    print(){
        alert(this.stack.join(","));
    }

}

document.addEventListener('keypress', (e) =>{
    if(e.key == 'Enter'){
        eval();
    } else {
        document.getElementById("textbox").placeholder = ""
    }
})

const append = num => {
    textbox = document.getElementById("textbox");
    textbox.value += num;
}

const del = () => {
    textbox = document.getElementById("textbox");
    text = String(textbox.value);
    if(text.length > 1){
        textbox.value = text.substring(0, text.length-1);
    } else {
        textbox.value = "";
    }
}

const empty = () => {
    document.getElementById("textbox").value = "";
}

const eval = () => {
    let expression = document.getElementById("textbox").value;
    let expArray = expression.split(" ");
    let stack = new Stack();
    for(let i =0; i < expArray.length; i++){
        let curr = expArray[i];
        if(!isNaN(curr)){
            stack.push(curr);
        } else {
            let b = Number(stack.pop())
            let a = Number(stack.pop())
            if(!a || !b){
                //alert("Ill Formed Expression");
                stack.clear();
                break;
            }
            if(curr == "+"){
                //alert(a + b);
                stack.push(a + b);
            } else if(curr == "-"){
                //alert(a - b)
                stack.push(a - b)
            } else if(curr == "*"){
                //alert(a * b)
                stack.push(a * b)
            } else if(curr == "/"){
                //alert(a / b)
                stack.push(a / b)
            } else if(curr == "^"){
                //alert(Math.pow(a, b))
                stack.push(Math.pow(a, b))
            } else {
                stack.clear();
                break;
            }
        }
    }
    if(stack.peek()){
        document.getElementById("textbox").value = stack.peek();
    } else {
        document.getElementById("textbox").placeholder = "Ill-Formed Expression";
    }
}


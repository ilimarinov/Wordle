const words = document.getElementsByClassName("wordJs")
const output = document.getElementsByClassName("hiddenWordJs")

if (words.length > 0) {
    for (let i = 0; i < words.length; i++) {
        const wordText = words[i].innerHTML
        const outputText = output[i].innerHTML
        words[i].innerHTML = ""
        for (let j = 0; j < wordText.length; j++) {
            const span = document.createElement("span")
            span.innerHTML = wordText[j]
            if (outputText[j] === "*") {
                span.className = "inPosition"
            }
            if (outputText[j] === "+") {
                span.className = "notInPosition"
            }
            words[i].append(span)
        }
    }
}



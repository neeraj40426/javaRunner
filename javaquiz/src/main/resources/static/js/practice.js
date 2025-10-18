document.addEventListener("DOMContentLoaded", () => {
    // Check if topic is passed via query param
    const urlParams = new URLSearchParams(window.location.search);
    const topic = urlParams.get('topic');

    if(topic) {
        loadQuestions(topic);
    }

    // Existing behavior for topic clicks on practice page
    document.querySelectorAll("[data-topic]").forEach(el => {
        el.addEventListener("click", () => loadQuestions(el.dataset.topic));
    });
});
const notRunnable = ["applets", "awt", "delegate_event_model", "java_database_connectivity", "packages"];

function isRunnable(topic) {
    return !notRunnable.includes(topic.toLowerCase());
}

async function loadQuestions(topic) {
    const introSection = document.getElementById("intro-section");
    const questionSection = document.getElementById("question-section");
    const notRunnable = [
        "applets",
        "awt",
        "delegate_event_model",
        "java_database_connectivity",
        "packages"
    ];

    if (introSection) introSection.style.display = "none";
    if (questionSection) questionSection.style.display = "block";

    function isRunnable(topic) {
        return !notRunnable.includes(topic.toLowerCase());
    }

    try {
        const res = await fetch(`/questions/${topic}`);
        if (!res.ok) throw new Error(`HTTP ${res.status}`);
        const questions = await res.json();

        const container = document.getElementById("questions-container");
        container.innerHTML = "";

        questions.forEach(q => {
            const card = document.createElement("div");
            card.className = "question-card";

            const questionText = document.createElement("p");
            questionText.innerHTML = `<strong>${q.questionno}. ${q.question}</strong>`;

            const btnShow = document.createElement("button");
            btnShow.textContent = "Show Code";
            btnShow.className = "show-code-btn";

            const editorContainer = document.createElement("div");
            editorContainer.style.display = "none";

            const codeArea = document.createElement("textarea");
            editorContainer.appendChild(codeArea);

            let btnRun, input, output, runMess;

            if (isRunnable(topic)) {
                btnRun = document.createElement("button");
                btnRun.textContent = "Run Code";
                btnRun.className = "run-code-btn";
                editorContainer.appendChild(btnRun);

                runMess = document.createElement("pre");
                runMess.textContent = "⚠️ To run the code, rename class to Main";
                editorContainer.appendChild(runMess);

                input = document.createElement("textarea");
                input.className = "input";
                input.value = "Enter the required inputs here (line by line)";
                input.style.display = "none";
                editorContainer.appendChild(input);

                output = document.createElement("pre");
                output.className = "output";
                output.style.display = "none";
                editorContainer.appendChild(output);
            }

            card.appendChild(questionText);
            card.appendChild(btnShow);
            card.appendChild(editorContainer);
            container.appendChild(card);

            const editor = CodeMirror.fromTextArea(codeArea, {
                mode: "text/x-java",
                theme: "dracula",
                lineNumbers: true,
                indentUnit: 4,
                tabSize: 4
            });

            // Show/hide editor & input/output
            btnShow.addEventListener("click", async () => {
                if (editorContainer.style.display === "none") {
                    editorContainer.style.display = "block";
                    if (input) input.style.display = "block";
                    if (output) output.style.display = "none";
                    btnShow.textContent = "Hide Code";

                    if (!editor.getValue()) {
                        try {
                            const codeRes = await fetch(`/questions/${topic}/${q.questionno}`);
                            const data = await codeRes.json();
                            editor.setValue(data.code || "No code available");
                        } catch {
                            editor.setValue("Error loading code.");
                        }
                    }
                } else {
                    editorContainer.style.display = "none";
                    btnShow.textContent = "Show Code";
                    if (output) output.style.display = "none";
                }
            });

            // Only attach run button if topic is runnable
            if (isRunnable(topic) && btnRun) {
                btnRun.addEventListener("click", () => runCode(editor, output, input));
            }
        });

    } catch (err) {
        console.error("Error loading questions:", err);
        alert("Failed to load questions.");
    }
}

async function runCode(editor, output, inputElement) {
    const code = editor.getValue().trim();
    const input = inputElement ? inputElement.value : "";

    if (!code) {
        output.textContent = "⚠️ Code is empty.";
        output.style.display = "block";
        output.style.color = "#ff4f4f";
        return;
    }

    output.textContent = "⏳ Running code...";
    output.style.display = "block";
    output.style.color = "#ccc";

    try {
        const response = await fetch(
            "https://judge0.p.rapidapi.com/submissions?base64_encoded=false&wait=true",
            {
                method: "POST",
                headers: {
                    "Content-Type": "application/json",
                    "X-RapidAPI-Key": "6915e780demsh8cb5edfc6fa4ea9p14e3f0jsncf4ba5fd6648",
                    "X-RapidAPI-Host": "judge0-ce.p.rapidapi.com"
                },
                body: JSON.stringify({
                    source_code: code,
                    language_id: 62,
                    stdin: input
                })
            }
        );

        const result = await response.json();

        if (result.status && result.status.description === "Compilation Error") {
            output.style.color = "#ff4f4f";
            output.textContent = "Compilation Error:\n" + (result.compile_output || "No details");
        } else if (result.stderr) {
            output.style.color = "#ff4f4f";
            output.textContent = "Runtime Error:\n" + result.stderr;
        } else if (result.stdout) {
            output.style.color = "#00ff9d";
            output.textContent = result.stdout;
        } else {
            output.style.color = "#ff4f4f";
            output.textContent = "Unknown error occurred";
        }
    } catch (err) {
        console.error("Execution error:", err);
        output.textContent = "⚠️ Failed to run code.";
        output.style.color = "#ff4f4f";
    }
}
window.addEventListener('scroll', function() {
    if(window.scrollY > 50) document.body.classList.add('scrolled');
    else document.body.classList.remove('scrolled');
});

window.addEventListener('scroll', function() {
    const sections = document.querySelectorAll('section');
    const triggerBottom = window.innerHeight * 0.85;

    sections.forEach(section => {
        const sectionTop = section.getBoundingClientRect().top;
        if(sectionTop < triggerBottom) {
            section.classList.add('visible');
        }
    });
});

// Run on load to catch sections already in viewport
window.dispatchEvent(new Event('scroll'));
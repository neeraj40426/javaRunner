document.addEventListener("DOMContentLoaded", () => {
    document.querySelectorAll(".show-code-btn").forEach(btn => {
        btn.addEventListener("click", async () => {
            const topic = btn.dataset.topic;
            const questionno = btn.dataset.questionno;
            const pre = document.getElementById("code-" + questionno);

            if (pre.style.display === "none" || pre.style.display === "") {
                try {
                    const res = await fetch(`/questions/${topic}/${questionno}`);
                    if (res.ok) {
                        const data = await res.json();
                        pre.textContent = data.code;
                        pre.style.display = "block";
                        btn.textContent = "Hide Code";
                    } else {
                        pre.textContent = "Code not found";
                        pre.style.display = "block";
                    }
                } catch (err) {
                    pre.textContent = "Error loading code";
                    pre.style.display = "block";
                }
            } else {
                pre.style.display = "none";
                btn.textContent = "Show Code";
            }
        });
    });
});

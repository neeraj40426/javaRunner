import com.example.javaquiz.Question;
import com.example.javaquiz.QuestionRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.io.*;
import java.nio.file.*;
import java.util.*;

@Component
public class QuestionImporter implements CommandLineRunner {

    private final QuestionRepository repo;

    public QuestionImporter(QuestionRepository repo) {
        this.repo = repo;
    }

    @Override
    public void run(String... args) throws Exception {
        Path codeBaseDir = Paths.get("C:/JavaProjectCodes");   // Git repo
        Path questionsDir = Paths.get("C:/QuestionsTxt");      // txt files

        Files.walk(codeBaseDir)
                .filter(Files::isDirectory)
                .forEach(topicDir -> {
                    try {
                        String topicName = topicDir.getFileName().toString();
                        if(topicName.equals(codeBaseDir.getFileName().toString())) return;

                        // Read questions from txt file
                        Path questionFile = questionsDir.resolve(topicName + ".txt");
                        if(!Files.exists(questionFile)) return;
                        List<String> questionLines = Files.readAllLines(questionFile)
                                .stream()
                                .map(String::trim)       // remove leading/trailing spaces
                                .filter(s -> !s.isEmpty()) // skip empty lines
                                .toList();               // Java 16+, or use collect(Collectors.toList()) for Java 11


                        // List all java files
                        List<Path> javaFiles = Files.list(topicDir)
                                .filter(Files::isRegularFile)
                                .filter(f -> f.toString().endsWith(".java"))
                                .toList();

                        // Topic-wise numbering starts from 1
                        for(int i=0; i<Math.min(questionLines.size(), javaFiles.size()); i++) {
                            String questionText = questionLines.get(i);
                            Path codeFile = javaFiles.get(i);
                            String code = String.join("\n", Files.readAllLines(codeFile));

                            Question q = new Question();
                            q.setTopic(topicName);
                            q.setQuestionno(i + 1);
                            q.setQuestion(questionText);
                            q.setCode(code);

                            repo.save(q);
                        }

                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                });

        System.out.println("All questions imported successfully!");
    }
}

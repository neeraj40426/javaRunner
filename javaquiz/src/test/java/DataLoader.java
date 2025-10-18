import com.example.javaquiz.Question;
import com.example.javaquiz.QuestionRepository;
import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Component;

@Component
public class DataLoader {

    private final QuestionRepository repo;

    public DataLoader(QuestionRepository repo) {
        this.repo = repo;
    }

    @PostConstruct
    public void loadDummyData() {
        repo.save(new Question(1, "Loops", "Print numbers 1 to 5", "for(int i=1;i<=5;i++){System.out.println(i);}"));
        repo.save(new Question(2, "Loops", "Print even numbers 1 to 10", "for(int i=1;i<=10;i++){if(i%2==0) System.out.println(i);}"));
        repo.save(new Question(1, "Arrays", "Declare int array of size 5", "int[] arr = new int[5];"));
    }
}

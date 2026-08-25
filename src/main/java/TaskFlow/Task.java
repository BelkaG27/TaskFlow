package TaskFlow;


import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;

@Entity
public class Task {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @NotBlank(message="Le titre ne peut pas être vide")
    @Size(max=100, message="Le titre ne peut pas dépasser 100 caractères")
    private String titre;

    @Size(max=500, message="La description ne peut pas dépasser 500 caractères")
    private String description;
    
    private boolean terminee;

    public Task(){};

    public Task(String titre, String description, boolean terminee) {
        this.titre = titre;
        this.description = description;
        this.terminee = terminee;
    }

    public int getId() {
        return id;
    }

    public void setId(int id){
        this.id=id;
    }

    public String getTitre() {
        return titre;
    }

    public void setTitre(String titre){
        this.titre=titre;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public boolean isTerminee() {
        return terminee;
    }

    public void setTerminee(boolean terminee) {
        this.terminee = terminee;
    }
}


import java.util.List;

public interface MagazineDAO {
    void addMagazine(Magazine magazine);
    Magazine getMagazineById(int id);
    Magazine getMagazineByName(String name);
    List<Magazine> getAllMagazines();
    void updateMagazine(Magazine magazine);
    void deleteMagazine(int id);
}

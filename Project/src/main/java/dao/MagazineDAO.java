import java.util.List;

public interface MagazineDAO {
    void addMagazine(Magazine magazine);
    Magazine getMagazineById(int id);
    List<Magazine> getAllMagazines();
    void updateMagazine(Magazine magazine);
    void deleteMagazine(int id);
}

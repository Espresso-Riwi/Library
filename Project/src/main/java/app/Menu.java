import javax.swing.*;
import java.time.LocalDate;
import java.util.List;

public class Menu {
    public void menu(){

        boolean flag = true;

        while (flag){
            String[] options = {"Books", "Magazines", "Items", "Loans", "Exit"};
            String option = JOptionPane.showInputDialog(null, "Welcome to the library menu", "Library Menu", JOptionPane.INFORMATION_MESSAGE, null, options, options[0]).toString();

            switch (option){
                case "Books":
                    String[] bookOptions = {"Show all books", "Search book by name", "Add book", "Update book"};
                    option = JOptionPane.showInputDialog(null, "Book Options", "Books Menu", JOptionPane.INFORMATION_MESSAGE, null, bookOptions, bookOptions[0]).toString();

                    switch (option){
                        case "Show all books":
                            showAllBooks();
                            break;
                        case "Search book by name":
                            getBookByName();
                            break;
                        case "Add book":
                            BookImp bookImp = new BookImp();
                            bookImp.addBook(addBook());
                            break;
                        case "Update book":
                            updateBook();
                            break;
                        default:
                            break;
                    }

                    break;
                case "Magazines":
                    String[] magazineOptions = {"Show all magazines", "Search magazine by name", "Add magazine", "Update magazine"};
                    option = JOptionPane.showInputDialog(null, "Magazine Options", "Magazine Menu", JOptionPane.INFORMATION_MESSAGE, null, magazineOptions, magazineOptions[0]).toString();

                    switch (option){
                        case "Show all magazines":
                            showAllMagazines();
                            break;
                        case "Search magazine by name":
                            break;
                        case "Add magazine":
                            MagazineImp magazineImp = new MagazineImp();
                            magazineImp.addMagazine(addMagazine());
                            break;
                        case "Update magazine":
                            updateMagazine();
                            break;
                        default:
                            break;
                    }
                    break;

                case "Items":
                    String[] itemsOptions = {"Show all items"};
                    option = JOptionPane.showInputDialog(null, "Item Options", "Item Menu", JOptionPane.INFORMATION_MESSAGE, null, itemsOptions, itemsOptions[0]).toString();

                    switch (option){
                        case "Show all items":
                            showAllItems();
                            break;
                        default:
                            break;
                    }
                    break;

                case "Loans":
                    String[] loansOptions = {"Show all loans", "Loan", "Return"};
                    option = JOptionPane.showInputDialog(null, "Loans Options", "Loan Menu", JOptionPane.INFORMATION_MESSAGE, null, loansOptions, loansOptions[0]).toString();

                    switch (option){
                        case "Show all loans":
                            break;
                        case "Loan":
                            String[] loanType = {"Book", "Magazine", "Exit"};
                            option = JOptionPane.showInputDialog(null, "Loans Options", "Loan Menu", JOptionPane.INFORMATION_MESSAGE, null, loanType, loanType[0]).toString();
                            switch (option){
                                case "Book":
                                    bookLoan();
                                    break;
                                case "Magazine":
                                    break;
                                case "Exit":
                                    break;
                            }
                            break;
                        case "Return":
                            String[] returnType = {"Book", "Magazine", "Exit"};
                            option = JOptionPane.showInputDialog(null, "Loans Options", "Loan Menu", JOptionPane.INFORMATION_MESSAGE, null, returnType, returnType[0]).toString();
                            switch (option){
                                case "Book":
                                    returnBook();
                                    break;
                                case "Magazine":
                                    break;
                                case "Exit":
                                    break;
                            }
                            break;
                        default:
                            break;
                    }
                    break;

                case "Exit":
                    flag = false;
                    break;
                default:
                    break;

            }
        }
    }

    public void showAllItems(){
        ItemImp itemImp = new ItemImp();
        List<Item> itemList = itemImp.getAllItems();

        String items = "";
        for (Item i : itemList){
            items += i.toString()+"\n\n";
        }

        JOptionPane.showMessageDialog(null, items, "Items", JOptionPane.INFORMATION_MESSAGE);
    }

    public void showAllBooks(){
        BookImp bookImp = new BookImp();
        List<Book> bookList = bookImp.getAllBooks();

        String books = "";
        for (Book b : bookList){
            books += b.toString()+"\n\n";
        }

        JOptionPane.showMessageDialog(null, books, "Books", JOptionPane.INFORMATION_MESSAGE);
    }

    public void showAllMagazines(){
        MagazineImp magazineImp = new MagazineImp();
        List<Magazine> magazineList = magazineImp.getAllMagazines();

        String magazines = "";
        for (Magazine m : magazineList){
            magazines += m.toString()+"\n\n";
        }

        JOptionPane.showMessageDialog(null, magazines, "Magazines", JOptionPane.INFORMATION_MESSAGE);
    }

    public void showAllLoans(){
        LoanImp loanImp = new LoanImp();
        List<Loan> loanList = loanImp.getAllLoans();

        String loans = "";
        for (Loan l : loanList){
            loans += l.toString()+"\n\n";
        }

        JOptionPane.showMessageDialog(null, loans, "Magazines", JOptionPane.INFORMATION_MESSAGE);
    }

    public Book addBook(){
        String title = JOptionPane.showInputDialog(null, "Title: ", "Title", JOptionPane.QUESTION_MESSAGE);
        String author = JOptionPane.showInputDialog(null, "Author: ", "Author", JOptionPane.QUESTION_MESSAGE);
        String isbn = JOptionPane.showInputDialog(null, "ISBN: ", "ISBN", JOptionPane.QUESTION_MESSAGE);
        String editorial = JOptionPane.showInputDialog(null, "Editorial: ", "Editorial", JOptionPane.QUESTION_MESSAGE);

        return new Book(title, author, isbn, editorial);
    }

    public Book getBookByName(){
        String name = JOptionPane.showInputDialog(null, "Book name: ", "Title", JOptionPane.QUESTION_MESSAGE);
        BookImp bookImp = new BookImp();
        return bookImp.getBookByName(name);
    }

    public void updateBook(){
        Book book = getBookByName();

        String[] options = {"Title", "Author", "ISBN", "Editorial", "Exit"};
        String option = JOptionPane.showInputDialog(null, "What do you want to edit?", "Book update", JOptionPane.INFORMATION_MESSAGE, null, options, options[0]).toString();

        switch (option){
            case "Title":
                String title = JOptionPane.showInputDialog(null, "Enter a new title", book.getTitle());
                book.setTitle(title);
                break;
            case "Author":
                book.setAuthor(JOptionPane.showInputDialog(null, "Enter a new author", book.getAuthor()));
                break;
            case "ISBN":
                book.setIsbn(JOptionPane.showInputDialog(null, "Enter a new ISBN", book.getIsbn()));
                break;
            case "Editorial":
                book.setEditorial(JOptionPane.showInputDialog(null, "Enter a new editorial", book.getEditorial()));
                break;
            case "Exit":
                break;
        }

        BookImp bookImp = new BookImp();
        bookImp.updateBook(book);
    }

    public Magazine addMagazine(){
        String title = JOptionPane.showInputDialog(null, "Title: ", "Title", JOptionPane.QUESTION_MESSAGE);
        String author = JOptionPane.showInputDialog(null, "Author: ", "Author", JOptionPane.QUESTION_MESSAGE);
        String issue_number = JOptionPane.showInputDialog(null, "Issue Number: ", "Issue Number", JOptionPane.QUESTION_MESSAGE);
        int issue_numberParsed = Integer.parseInt(issue_number);
        String topic = JOptionPane.showInputDialog(null, "Topic: ", "Topic", JOptionPane.QUESTION_MESSAGE);

        return new Magazine(title, author, issue_numberParsed, topic);
    }

    public Magazine getMagazineByName(){
        String name = JOptionPane.showInputDialog(null, "Book name: ", "Title", JOptionPane.QUESTION_MESSAGE);
        MagazineImp magazineImp = new MagazineImp();

        return magazineImp.getMagazineByName(name);
    }

    public void updateMagazine(){
        Magazine magazine = getMagazineByName();

        String[] options = {"Title", "Author", "Issue Number", "Topic", "Exit"};
        String option = JOptionPane.showInputDialog(null, "What do you want to edit?", "Magazine update", JOptionPane.INFORMATION_MESSAGE, null, options, options[0]).toString();

        switch (option){
            case "Title":
                String title = JOptionPane.showInputDialog(null, "Enter a new title", magazine.getTitle());
                magazine.setTitle(title);
                break;
            case "Author":
                magazine.setAuthor(JOptionPane.showInputDialog(null, "Enter a new author", magazine.getAuthor()));
                break;
            case "Issue Number":
                magazine.setIssueNumber(Integer.parseInt(JOptionPane.showInputDialog(null, "Enter a new ISBN", magazine.getIssueNumber())));
                break;
            case "Topic":
                magazine.setTopic(JOptionPane.showInputDialog(null, "Enter a new editorial", magazine.getTopic()));
                break;
            case "Exit":
                break;
        }

        MagazineImp magazineImp = new MagazineImp();
        magazineImp.updateMagazine(magazine);
    }


    //It works, just do the logic to recognize the book and the user.
    public void bookLoan(){
        Loan loan = new Loan(1, 1, LocalDate.now(), LocalDate.now().plusDays(10));
        LoanImp loanImp = new LoanImp();

        loanImp.addLoan(loan);
    }

    public void MagazineLoan(){
        Loan loan = new Loan(1, 1, LocalDate.now(), LocalDate.now().plusDays(10));
        LoanImp loanImp = new LoanImp();

        loanImp.addLoan(loan);
    }

    //It works, just do the logic to recognize the book and the user.
    public void returnBook(){
        LoanImp loanImp = new LoanImp();
        loanImp.markAsReturned(1);
    }
}

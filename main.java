import java.sql.*;
import java.util.*;
public class main {
    static Scanner scanner= new Scanner(System.in);
    public static void main(String[] args)throws ClassNotFoundException, SQLException {
        String url="jdbc:mysql://localhost/Library1";
        String user="root";
        String password="Jahswill@2020";
        Connection conn= DriverManager.getConnection(url,user,password);

        while (true){
            System.out.println("Enter 1 to add books\n" +
                    "Enter 2 to view all books\n" +
                    "Enter 3 to borrow books\n" +
                    "Enter 4 to return borrowed books\n" +
                    "Enter 5 for a list of borrowed books\n" +
                    "Enter 6 for a list of available books\n" +
                    "Enter 8 to delete  books\n"+
                    "Enter 7 to quit");
            String choice=scanner.nextLine();
            if(choice.equals("7")){
                break;
            }
            else if(choice.equals("1")){
                while (true){
                System.out.println("Enter book name(press q to quit)");
                String name=scanner.nextLine();
                if(name.trim().equalsIgnoreCase("q")){
                    break;
                }
                else{
                    System.out.println("Enter book author");
                    String author=scanner.nextLine();
                    System.out.println("Enter book isbn");
                    int isbn =scanner.nextInt();
                    scanner.nextLine();
                    Statement st= conn.createStatement();
                    int rt= st.executeUpdate("insert into info(bookname,author,isbn)" +
                            "values('"+name+"','"+author+"',"+isbn+")");

                }
            }}
            else if(choice.equals("2")){
                while (true){
                Statement st1=conn.createStatement();
                ResultSet ru=st1.executeQuery("select * from info");
                while(ru.next()){
                    System.out.println("--------------------------------");
                    System.out.println("bookname: "+(ru.getString("bookname")));
                    System.out.println("Author+: "+ru.getString("author"));
                    System.out.println("isbn: "+ru.getInt("isbn"));
                    System.out.println("Status: "+ru.getString("status"));
                    System.out.println("--------------------------------");

                    }
                System.out.println("press r to return to main menu");
                String exit=scanner.nextLine();
                if(exit.trim().equalsIgnoreCase("r")){
                    break;
                }
            }}
            else if(choice.equals("8")){
                while(true){
                System.out.println("press 1 to delete a book\n" +
                        "press 2 to delete all books\n " );
                String delete=scanner.nextLine();


                 if(delete.trim().equalsIgnoreCase("1")){
                    System.out.println("Enter isbn of book to delete");
                    int deleteIsbn=scanner.nextInt();
                    Statement st3=conn.createStatement();
                    st3.executeUpdate("delete from info where isbn="+deleteIsbn);

                }
                else if(delete.trim().equalsIgnoreCase("2")){
                    Statement st4=conn.createStatement();
                    st4.executeUpdate("delete from info");
                }
                    System.out.println("press r to return to main menu");
                String exit2=scanner.nextLine();
                if(exit2.trim().equalsIgnoreCase("r")){
                    break;
                }
            }}
            else if(choice.equals("3")){
                while(true){
                    System.out.println("enter isbn number of book you wish to borrow");
                    int isbn=scanner.nextInt();
                    scanner.nextLine();
                    Statement st2=conn.createStatement();
                    ResultSet ru=st2.executeQuery("select * from info where isbn="+isbn);
                    while(ru.next()) {
                        if (ru.getString("Status").equalsIgnoreCase("borrowed")) {
                            System.out.println("you have already borrowed book");
                        } else {
                            Statement st5 = conn.createStatement();
                            int r5=st5.executeUpdate("update info set status='borrowed' where isbn=" + isbn);

                        }

                    }
                    System.out.println("press r to return to main menu");
                    String exit3 = scanner.nextLine();
                    if (exit3.trim().equalsIgnoreCase("r")) {
                        break;
                    }

            }}
            else if(choice.equals("4")){
                while(true){
                    System.out.println("enter isbn number of book you wish to return");
                    int isbn=scanner.nextInt();
                    scanner.nextLine();
                    Statement st5=conn.createStatement();
                    Statement st6=conn.createStatement();
                    ResultSet ru=st6.executeQuery("select * from info where isbn="+isbn);
                    while(ru.next()) {
                        if(ru.getString("status").equalsIgnoreCase("available")) {
                            System.out.println("you have already returned book");
                        }
                        else{
                    st5.executeUpdate("update info set status='available' where isbn="+isbn);

                    }

                }
                    System.out.println("press r to return to main menu");
                    String exit3=scanner.nextLine();
                    if(exit3.trim().equalsIgnoreCase("r")){
                        break;

            }

            }
        }

            else if(choice.equals("5")){
                while(true){
                    Statement st7=conn.createStatement();
                    ResultSet rs2=st7.executeQuery("select * from info where status='borrowed'");
                    while(rs2.next()) {
                        System.out.println("-------------------------------");
                        System.out.println("bookname: " + (rs2.getString("bookname")));
                        System.out.println("Author+: " + (rs2.getString("author")));
                        System.out.println("isbn: " + (rs2.getString("isbn")));
                        System.out.println("Status: " + (rs2.getString("status")));
                    }
                    System.out.println("press r to return to main menu");
                    String exit4=scanner.nextLine();
                    if(exit4.trim().equalsIgnoreCase("r")){
                        break;
                    }
                }
            }
            else if(choice.equals("6")){
                while(true){
                    Statement st6=conn.createStatement();
                    ResultSet rs=st6.executeQuery("select * from info where status='available'");
                    while(rs.next()) {
                        System.out.println("-------------------------------");
                        System.out.println("bookname: " + (rs.getString("bookname")));
                        System.out.println("Author+: " + (rs.getString("author")));
                        System.out.println("isbn: " + (rs.getString("isbn")));
                        System.out.println("Status: " + (rs.getString("status")));
                    }
                    System.out.println("press r to return to main menu");
                    String exit4=scanner.nextLine();
                    if(exit4.trim().equalsIgnoreCase("r")){
                        break;
                    }
                }
            }
        }

    }
}

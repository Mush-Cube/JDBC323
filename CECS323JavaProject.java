package cecs323javaproject;
import java.sql.*;
import java.util.Scanner;
/**********************************************
 *    Name: Roberto Sanchez
 *    Name: Longcheng Chi
 *    Date: Oct 09, 2016
 * Project: JDBC Project 
 **********************************************/
public class CECS323JavaProject {
    static final String displayFormat = "%-45s%-20s%-20s%-20s\n";
    static final String JDBC_DRIVER = "org.apache.derby.jdbc.ClientDriver";
    static String DB_URL = "jdbc:derby://localhost:1527/JDBC Project;user=APP;password=d1am0nd";//Yours may look different
    public static String dispNull (String input){
        if ( input == null || input.length()== 0)
            return "N/A";
        else
            return input;
    }
    public static void main(String[] args) {
        Connection conn = null;
        Statement stmt = null;
        ResultSet rs = null;
        boolean Menusession= true;
        Scanner input = new Scanner(System.in); 
        try{
            //STEP 2: Register JDBC driver
            Class.forName("org.apache.derby.jdbc.ClientDriver");
            //STEP 3: Open a connection
            System.out.println("Connecting to database...");
            conn = DriverManager.getConnection(DB_URL);
            System.out.println("Connection Established!");
            //STEP 4: Set up menu for JDBC Project
            while(Menusession){
                System.out.print("\tSQL Menu:\n"
                    + "========================\n"
                    + "1. Insert a new BOOK\n"
                    + "2. Insert a new Publisher\n"
                    + "3. List all writing groups\n"
                    + "4. List the data for a group specified by the user\n"
                    + "5. List all publishers\n"
                    + "6. List the data for a publisher specified by the user\n"
                    + "7. List all book titles\n"
                    + "8. List the data for a book specified by the user\n"
                    + "9. Remove a book\n"
                    + "10. End Session\n"
                    + "11. DEMO\n"
                    + "========================\n"
                    + "\tEnter selection: ");
                String selection = input.nextLine(); 
                switch (selection) {
                        case "1":
                          stmt = conn.createStatement();
    			  System.out.println("Enter the title of the new book to add(limit 255 characters)");
    			  String booktitle = input.nextLine();
    			  System.out.println("Enter the published year of the book (YYYY)");
    			  String yearpublished = input.nextLine();
    			  System.out.println("Enter the the number of pages");
    			  String numberpages = input.nextLine();
    			  System.out.println("Enter the writing group's name of this book");
    			  String groupname = input.nextLine();
    			  System.out.println("Enter the publisher's name");
    			  String publishername = input.nextLine();
    			 
    			  System.out.println("Creating statement...");
    			  String sql = "INSERT INTO BOOK (BOOKTITLE, GROUPNAME, YEARPUBLISHED, NUMBERPAGES, PUBLISHERNAME) VALUES " +
    			  		"(?,?,?,?,?)";
                          PreparedStatement pstmt = conn.prepareStatement(sql);
                          pstmt.setString(1, booktitle);
                          pstmt.setString(2, groupname);
                          pstmt.setString(3, yearpublished);
                          pstmt.setString(4, numberpages);
                          pstmt.setString(5, publishername);
    			  pstmt.executeUpdate();
                                break;
                        case "2":
                          stmt = conn.createStatement();
    			  System.out.println("Enter the name of the new publisher(limit 255 characters)");
    			  String pubname = input.next();
    			  System.out.println("Enter the address of the new publisher");
    			  String pubaddress = input.next();
    			  System.out.println("Enter the the phonenumber of the new publisher");
    			  String pubphone = input.next();
    			  System.out.println("Enter the email of the new publisher");
    			  String pubemail = input.next();
                          System.out.println("Enter name of publisher to replace");
    			  String replace = input.next();
    			 
    			  System.out.println("Creating statement...");
    			  sql = "INSERT INTO PUBLISHER (PUBLISHERNAME, PUBLISHERADDRESS, PUBLISHERPHONE, PUBLISHERMAIL) VALUES " +
    			  		"('"+pubname+"', '"+pubaddress+"', '"+pubphone+"', "+pubemail+"')";
    			  stmt.executeUpdate(sql);
                          String sql2 = "UPDATE BOOK SET PUBLISHERNAME='"+pubname+"' WHERE PUBLISHERNAME='"+replace +"'";
    			  stmt.executeUpdate(sql2);
                                break;
                        case "3":
                                // LIST ALL Writing Groups
    		      System.out.println("Creating statement...\n");
    		      stmt = conn.createStatement();

    		      sql = "SELECT Groupname FROM WritingGroup";
    		      rs = stmt.executeQuery(sql);
    		      //STEP 5: Extract data from result set
    		      System.out.println("Groupname:");
    		      while(rs.next()){
    		         //Retrieve by column name2
    		         String first = rs.getString("GROUPNAME");

    		         //Display values
    		         System.out.println(first);
    		      }
    		      System.out.println();
    		      rs.close();
                                break;
                      case "4":
                                //LIST DATA FOR a WRITINGGROUP
    			  System.out.println("Enter the name of the writing group to find: ");
    			  String WGChoice = input.next();
    			  System.out.println("Creating statement...");
                          stmt = conn.createStatement();
    			  sql = "SELECT GROUPNAME, HEADWRITER, YEARSFORMED, SUBJECT FROM WRITINGGROUP WHERE GROUPNAME='"+WGChoice+"'";
    			  rs = stmt.executeQuery(sql);
    			  while(rs.next()) {
    				  String GROUPNAME = rs.getString("GROUPNAME");
    				  String HEADWRITER = rs.getString("HEADWRITER");
    				  String YEARSFORMED = rs.getString("YEARSFORMED");
    				  String SUBJECT = rs.getString("SUBJECT");
    				  
    				  System.out.println("\nGROUPNAME: "+GROUPNAME+"\nHEADWRITER: "+HEADWRITER+"\nYEARSFORMED: "+YEARSFORMED+"\nSUBJECT: "+SUBJECT+"\n");
    			  }
                                break;
                        case "5":
                                // LIST ALL Publishers
    		      System.out.println("Creating statement...\n");
    		      stmt = conn.createStatement();

    		      sql = "SELECT publishername FROM PUBLISHER";
    		      rs = stmt.executeQuery(sql);
    		      //STEP 5: Extract data from result set
    		      System.out.println("publishername:");
    		      while(rs.next()){
    		         //Retrieve by column name2
    		         String first = rs.getString("publishername");

    		         //Display values
    		         System.out.println(first);
    		      }
    		      System.out.println();
    		      rs.close();
                                break;
                      case "6":
                                //LIST DATA FOR a PUBLISHER
    			  System.out.println("Enter the name of the publisher to find: ");
    			  String PUBChoice = input.next();
    			  System.out.println("Creating statement...");
                          stmt = conn.createStatement();
    			  sql = "SELECT PUBLISHERNAME, PUBLISHERADDRESS, PUBLISHERPHONE, PUBLISHERMAIL FROM PUBLISHER WHERE PUBLISHERNAME='"+PUBChoice+"'";
    			  rs = stmt.executeQuery(sql);
    			  while(rs.next()) {
    				  String PUBLISHERNAME = rs.getString("PUBLISHERNAME");
    				  String PUBLISHERADDRESS = rs.getString("PUBLISHERADDRESS");
    				  String PUBLISHERPHONE = rs.getString("PUBLISHERPHONE");
    				  String PUBLISHERMAIL = rs.getString("PUBLISHERMAIL");
    				  
    				  System.out.println("\nPUBLISHERNAME: "+PUBLISHERNAME+"\nPUBLISHERADDRESS: "+PUBLISHERADDRESS+"\nPUBLISHERPHONE: "+PUBLISHERPHONE+"\nPUBLISHERMAIL: "+PUBLISHERMAIL+"\n");
    			  }
                                break;
                        case "7":
                                    // LIST ALL book titles
                          System.out.println("Creating statement...\n");
                          stmt = conn.createStatement();

                          sql = "SELECT BOOKTITLE FROM BOOK";
                          rs = stmt.executeQuery(sql);
                          //STEP 5: Extract data from result set
                          System.out.println("BOOKTITLE:");
                          while(rs.next()){
                             //Retrieve by column name2
                             String first = rs.getString("BOOKTITLE");

                             //Display values
                             System.out.println(first);
                          }
                          System.out.println();
                          rs.close();
                                break;
                        case "8":
                            //LIST all DATA FOR a Book
    			  System.out.println("Enter the name of the book to find: ");
    			  String BChoice = input.next();
    			  System.out.println("Creating statement...");
                          stmt = conn.createStatement();
    			  sql = "SELECT BOOKTITLE, YEARPUBLISHED, NUMBERPAGES, GROUPNAME, PUBLISHERNAME FROM BOOK WHERE BOOKTITLE='"+BChoice+"'";
    			  rs = stmt.executeQuery(sql);
    			  while(rs.next()) {
    				  String BOOKTITLE = rs.getString("BOOKTITLE");
    				  String YEARPUBLISHED = rs.getString("YEARPUBLISHED");
    				  String NUMBERPAGES = rs.getString("NUMBERPAGES");
    				  String GROUPNAME = rs.getString("GROUPNAME");
                                  String PUBLISHERNAME = rs.getString("PUBLISHERNAME");
    				  
    				  System.out.println("\nBOKTITLE: "+BOOKTITLE+"\nYEARPUBLISHED: "+YEARPUBLISHED+"\nNUMBERPAGES: "
                                                                   +NUMBERPAGES+"\nGROUPNAME: "+GROUPNAME+"\nPUBLISHERNAME: "+PUBLISHERNAME+"\n");
    			  }
                            
                                break;
                        case "9":
                               stmt = conn.createStatement();
                               //REMOVE
                               System.out.println("Enter the name of the book to be deleted");
                               String title = input.nextLine();
                               System.out.println("Enter the name of the group");
                               String group = input.nextLine();
                               sql = "DELETE FROM BOOK WHERE BOOKTITLE='"+title +"' AND GROUPNAME='"+group+"'";
                                stmt.executeUpdate(sql);
                               break; 
                        case "10":
                               Menusession = false;
                               System.out.println("Exiting System...\n"+"Connections will be closed...");
                               break; 
                        default:
                            System.out.println("Not a valid option, try again...\n");
                }
            }
            //STEP 5: Clean up enviroment after finish
            rs.close();
            stmt.close();
            conn.close();
        } catch (SQLException se) {
            //Handle errors for JDBC
            se.printStackTrace();
        } catch (Exception e) {
            //Handle errors for Class.forName
            e.printStackTrace();
        } finally {
            //finally block used to close resources
            try {
                if (stmt != null) {
                    stmt.close();
                }
            } catch (SQLException se2) {
            }// nothing we can do
            try {
                if (conn != null) {
                    conn.close();
                }
            } catch (SQLException se) {
                se.printStackTrace();
            }//end finally try
        }//end try
        System.out.println("Done.");
        }
    public static void Create(){
         System.out.println("Create Option was selected!");
    }
    public static void Read(){
        
        System.out.println("Read Option was selected!");
    }
    public static void Update(){
        System.out.println("Update Option was selected!");
    }
    public static void Delete(){
        System.out.println("Delete Option was selected!");
    }
    public static void setwLF(String str, int width, char fill){        
        for (int x = str.length(); x < width; ++x) 
        {
            System.out.print(fill); 
        }
        System.out.print(str);
    }// end of setwLF
    }

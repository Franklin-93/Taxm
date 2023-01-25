
                        package tax;
                        import java.io.BufferedReader;
                        import java.io.IOException;
                        import java.io.InputStreamReader;

                        /**
                         *
                         * @author FRANKLIN
                         */
                        public class Tax {

                            /**
                             * @param args the command line arguments
                             */
                            public static void main(String[] args) throws IOException {

                            BufferedReader kb = new BufferedReader(new InputStreamReader(System.in));

                                                           
                            Calculation singlePerson = new Calculation();

                            singlePerson.singlePersonTax();

                           //singlePerson.weeklyTaxCredits();
                           //singlePerson.grossDeduction();
                           //singlePerson.grossPayLimit();
                            
   /*
    // Main users MENU
    String userChoice ="Please Choose: " + "\n"
                + "------------------" + "\n"
                + "1: Tax Calculation" + "\n"
                + "2: Financial Planning";
     // prompting user option
    System.out.println("Hello there , " + "\n" + userChoice);
    int userChoiceII = Integer.parseInt(kb.readLine());


    if (userChoiceII == 1){
            String userMenu ="What is your Status:" + "\n"
                + "------------" + "\n"
                + "1: Single" + "\n"
                + "2: Married";

            // prompting user option
            System.out.println("Welcome to your Tax Calculation! " + "\n" 
                    + "\n" 
                    + "The purpose of Tax Calculation is just that. To find out whether you are paying more tax than you " + "\n" 
                    + "should and how to fix it. In most cases, specifically foreigners who can only part-time (20 hrs) and " + "\n" 
                    + "has multiple employers in which very often are being taxed at 40% called (Emergency Tax). " + "\n"
                    + "\n" 
                    + "In order for us to find out the amount of tax that best suits you, or whether you are currently working for "  + "\n"
                    + "more than one employer. Some information is required as depending on your status the (Tax Credits) apply : " + "\n" 
                    + "\n"
                    + userMenu);

               int userInput = Integer.parseInt(kb.readLine());

                // user option switch 
                switch(userInput){
                    case 1:
                        System.out.println("Single");
                        break;
                    case 2:
                        System.out.println("Married");
                        break;

                    default:// do nothing    
    }
  }
*/






                        }

                        }


                    package tax;
                    import java.io.BufferedReader;
                    import java.io.IOException;
                    import java.io.InputStreamReader;
                    import java.text.DecimalFormat;

                    public class Calculation {

                       final private double rateBand1;
                       final private double yearlyTaxCredits;
                       final private int yearlyWeeks; 
                       final private int yearlyWeeksII;
                       final private int yearlyWeeksIII;
                       final private int weeklyPayLimit; 
                       final private int fortnighlyPayLimit; 
                       final private int monthlyPayLimit; 
                       final private double percentage;
                       final private int regularTaxPercentage;
                       final private int emergencyTaxPercentage;
                       final private double taxCreditsTwoEmployeers;

                    // Global decimal format to display double in 2 decimal place 
                    public static DecimalFormat df = new DecimalFormat("0.00");

                    //Global buffered Reader
                    public static BufferedReader myKeyboard = new BufferedReader(new InputStreamReader(System.in));                       

                    // default constructor (initialised)  
                    public Calculation(){
                        
                        this.rateBand1 = 40000;
                        this.yearlyTaxCredits = 3500;
                        this.yearlyWeeks = 52; 
                        this.yearlyWeeksII = 26;
                        this.yearlyWeeksIII = 12;
                        this.weeklyPayLimit = 52;
                        this.fortnighlyPayLimit = 26;
                        this.monthlyPayLimit = 12;
                        this.percentage = 100; 
                        this.regularTaxPercentage = 20;
                        this.emergencyTaxPercentage = 40;
                        this.taxCreditsTwoEmployeers = this.yearlyTaxCredits + 1775;                        
                    }
                       public void singlePersonTax() throws IOException{
                           
                        int numberOfEmployers=0;
                        int taxCredits=0;

                        // get number of employers
                        System.out.println("How many employers are currently working for?");
                        numberOfEmployers = Integer.parseInt(myKeyboard.readLine());                         

                        // storing each employers tax credits based on numbers of employers entered by the user
                        int employersTaxCredit [] = new int [numberOfEmployers];

                        // storing employers name based on numbers of employers entered by the user
                        String companyName [] = new String [numberOfEmployers];

                            // reading employers name through numbers of employers 
                            for(int counter=0; counter < numberOfEmployers; counter++){
                                System.out.println("Employer " +(counter +1) + " Name:");
                                companyName[counter] = myKeyboard.readLine();
                            }

                            // reading employer tax credit through numbers os employers
                            for(int i=0; i < numberOfEmployers; i++){
                                System.out.println("Enter the Tax Credits for => " + companyName[i]);
                                employersTaxCredit[i] = Integer.parseInt(myKeyboard.readLine());
                            }

                           //System.out.println("Here is the breakdown of your TAX CREDITS for each employer" + "\n");
                           for(int i=0; i<companyName.length; i++) {
                               
                            // Payment frequency menu   
                            String userPrompt ="How often are getting paid by " + companyName[i] + " ? " + "\n"
                                        + "Please Enter: " + "\n"
                                        + "------------- " + "\n"
                                        + "1: Weekly " + "\n"
                                        + "2: Fortnightly " + "\n" 
                                        + "3: Monthly "+ "\n";

                            // output message for the user
                            System.out.println(userPrompt); 

                            // get user input
                            int paymentFrequency = Integer.parseInt(myKeyboard.readLine());

                            double grossDeduction = 0; 
                            double weeklyGrossPay = 0;
                            double fortnightlyGrossPay = 0;
                            double monthlyGrossPay = 0;
                            double hoursPerWeek = 0;
                            double salaryPerHour = 0;
                            double weeklyTaxCrdits = 0;
                            double remainingBalance = 0;
                            
                            // limit is set up by the rate band 1 = 40,000.00 divided by how often a employee is getting paid 
                            double weeklyGrossPayLimit = 0; // weekly limit = 40,000.00 / 52 
                            double fortnightlyGrossPayLimit = 0; // fortnightly limit = 40,000.00 / 26 
                            double monthlyGrossPayLimit = 0; // monthly limit = 40,000.00 / 26
                            double netDeduction_20 = 0;
                            double netDeduction_40 = 0;
                            double totalDeduction = 0;
                            double NET_PAY = 0;

                            // Payment Frequency statement
                            switch (paymentFrequency){
                                
                                // WEEKLY PAYMENT
                                case 1:
                                    // cause the user wont know exaclty how much their geting paid per hour
                                    System.out.println("Enter your Weekly Groos pay for " + companyName[i]);
                                    weeklyGrossPay = Double.parseDouble(myKeyboard.readLine());
                                    
                                    // hours per week
                                    System.out.println("How many hours do you usually work on a Weekly bases for " + companyName[i]);
                                    hoursPerWeek = Double.parseDouble(myKeyboard.readLine());
                                    
                                        // 1) finding gross pay limit by RATE BAND 1 / 52 weeks per year
                                        weeklyGrossPayLimit = this.rateBand1 / this.weeklyPayLimit;
                                            
                                        // check whether user is being taxed at emergency tax
                                        if (weeklyGrossPay > weeklyGrossPayLimit){

                                             ///////// CALCULATION AT 20% first ///////////////////
                                                                                         
                                            // 2) diference between gross pay and weekly limit = balance to be taxed at 40%
                                            remainingBalance = weeklyGrossPay - weeklyGrossPayLimit;
                                        
                                            // 3) finding gross deductions at 20%
                                            grossDeduction = weeklyGrossPayLimit * this.regularTaxPercentage / this.percentage;
                                                                                      
                                            // 4) finding the weekly tax credits
                                            weeklyTaxCrdits = this.yearlyTaxCredits / this.yearlyWeeks;
                                            
                                            // 5) finding NET Deductions
                                            netDeduction_20 = grossDeduction - weeklyTaxCrdits;
                                            
                                            ///////// CALCULATION AT 40% now ///////////////////
                                            
                                            // 6) calculating remaing balance from gross pay and limit per week
                                            netDeduction_40 = remainingBalance * this.emergencyTaxPercentage;
                                            
                                            // 7) total deduction = net_deduction 20 + net_deduction 40;
                                            totalDeduction = netDeduction_20 + netDeduction_40;
                                            
                                            // 8) finding NET PAY
                                            NET_PAY = weeklyGrossPay - totalDeduction;
                                 
                                            System.out.println("You are being taxed at (Emergency Tax) by " + companyName[i] + "."
                                                    + " Please, contact Revenue!!!" + "\n");
                                    }
                                        
                                        else if (weeklyGrossPay <= weeklyGrossPayLimit){
                                            
                                             ///////// CALCULATION AT 20% only ///////////////////
                                             
                                            // 1) finding gross deductions
                                            grossDeduction = weeklyGrossPayLimit * this.regularTaxPercentage / this.percentage;
                                            
                                            // 2) finding the weekly tax credits
                                            weeklyTaxCrdits = this.yearlyTaxCredits / this.yearlyWeeks;
                                            
                                            // 3) finding NET Deductions
                                            netDeduction_20 = grossDeduction - weeklyTaxCrdits;
                                            
                                            // 4) finding NET PAY AT 
                                            NET_PAY = weeklyGrossPay - totalDeduction;
                                            System.out.println("You are not at (Emergency Tax) by " + companyName[i] + "\n");
                                    }
                                        else{
                                            System.out.println("SOMETHING WENT WRONG!!!");
                                        }
                                    break;
                                    
                                    
                                // FORTNIGHLY PAYMENT   
                                case 2:
                                    // cause the user wont know exaclty how much their geting paid per hour
                                    System.out.println("Enter your Fortnightly Groos pay for " + companyName[i]);
                                    fortnightlyGrossPay = Double.parseDouble(myKeyboard.readLine());
                                    
                                    // hours per week
                                    System.out.println("How many hours do you usually work every two weeks for " + companyName[i]);
                                    hoursPerWeek = Double.parseDouble(myKeyboard.readLine());
                                    
                                        // 1) finding gross pay limit by RATE BAND 1 / 26 weeks per year
                                        fortnightlyGrossPayLimit = this.rateBand1 / this.fortnighlyPayLimit;

                                         // check whether user is being taxed at emergency tax
                                        if (fortnightlyGrossPay > fortnightlyGrossPayLimit){
                                           
                                            ///////// CALCULATION AT 20% first ///////////////////
                                                                                         
                                            // 2) diference between gross pay and weekly limit = balance to be taxed at 40%
                                            remainingBalance = fortnightlyGrossPay - fortnightlyGrossPayLimit;
                                        
                                            // 3) finding gross deductions at 20%
                                            grossDeduction = fortnightlyGrossPayLimit * this.regularTaxPercentage / this.percentage;
                                                                                      
                                            // 4) finding the weekly tax credits
                                            weeklyTaxCrdits = this.yearlyTaxCredits / this.yearlyWeeksII;
                                            
                                            // 5) finding NET Deductions
                                            netDeduction_20 = grossDeduction - weeklyTaxCrdits;
                                            
                                            ///////// CALCULATION AT 40% now ///////////////////
                                            
                                            // 6) calculating remaing balance from gross pay and limit per week
                                            netDeduction_40 = remainingBalance * this.emergencyTaxPercentage;
                                            
                                            // 7) total deduction = net_deduction 20 + net_deduction 40;
                                            totalDeduction = netDeduction_20 + netDeduction_40;
                                            
                                            // 8) finding NET PAY
                                            NET_PAY = fortnightlyGrossPay - totalDeduction;
                                 
                                            System.out.println("You are being taxed at (Emergency Tax) by " + companyName[i] + "."
                                                    + " Please, contact Revenue!!!" + "\n");
                                    }
                                        
                                        else if (fortnightlyGrossPay <= fortnightlyGrossPayLimit){
                                            
                                             ///////// CALCULATION AT 20% only ///////////////////
                                             
                                            // 1) finding gross deductions
                                            grossDeduction = fortnightlyGrossPayLimit * this.regularTaxPercentage / this.percentage;
                                            
                                            // 2) finding the weekly tax credits
                                            weeklyTaxCrdits = this.yearlyTaxCredits / this.yearlyWeeksII;
                                            
                                            // 3) finding NET Deductions
                                            netDeduction_20 = grossDeduction - weeklyTaxCrdits;
                                            
                                            // 4) finding NET PAY AT 
                                            NET_PAY = fortnightlyGrossPay - totalDeduction;
                                            System.out.println("You are not at (Emergency Tax) by " + companyName[i] + "\n");
                                    }
                                        else{
                                            System.out.println("SOMETHING WENT WRONG!!!");
                                        } 
                                    break;
                                    
                                case 3:
                                    // cause the user wont know exaclty how much their geting paid per hour
                                    System.out.println("Enter your Monthly Groos pay for " + companyName[i]);
                                    monthlyGrossPay = Double.parseDouble(myKeyboard.readLine());
                                    
                                    // hours per week
                                    System.out.println("How many hours do you usually work monthly for " + companyName[i]);
                                    hoursPerWeek = Double.parseDouble(myKeyboard.readLine());
                                    
                                        // finding gross pay limit by RATE BAND 1 / 12 months per year
                                        monthlyGrossPayLimit = this.rateBand1 / this.monthlyPayLimit;
                                        
                                    // check whether user is being taxed at emergency tax
                                    if (monthlyGrossPay > monthlyGrossPayLimit){
                                        
                                            ///////// CALCULATION AT 20% first ///////////////////
                                                                                         
                                            // 2) diference between gross pay and weekly limit = balance to be taxed at 40%
                                            remainingBalance = monthlyGrossPay - monthlyGrossPayLimit;
                                        
                                            // 3) finding gross deductions at 20%
                                            grossDeduction = monthlyGrossPayLimit * this.regularTaxPercentage / this.percentage;
                                                                                      
                                            // 4) finding the weekly tax credits
                                            weeklyTaxCrdits = this.yearlyTaxCredits / this.yearlyWeeksIII;
                                            
                                            // 5) finding NET Deductions
                                            netDeduction_20 = grossDeduction - weeklyTaxCrdits;
                                            
                                            ///////// CALCULATION AT 40% now ///////////////////
                                            
                                            // 6) calculating remaing balance from gross pay and limit per week
                                            netDeduction_40 = remainingBalance * this.emergencyTaxPercentage;
                                            
                                            // 7) total deduction = net_deduction 20 + net_deduction 40;
                                            totalDeduction = netDeduction_20 + netDeduction_40;
                                            
                                            // 8) finding NET PAY
                                            NET_PAY = monthlyGrossPay - totalDeduction;
                                 
                                            System.out.println("You are being taxed at (Emergency Tax) by " + companyName[i] + "."
                                                    + " Please, contact Revenue!!!" + "\n");
                                    }
                                        
                                        else if (monthlyGrossPay <= monthlyGrossPayLimit){
                                            
                                             ///////// CALCULATION AT 20% only ///////////////////
                                             
                                            // 1) finding gross deductions
                                            grossDeduction = monthlyGrossPayLimit * this.regularTaxPercentage / this.percentage;
                                            
                                            // 2) finding the weekly tax credits
                                            weeklyTaxCrdits = this.yearlyTaxCredits / this.yearlyWeeksIII;
                                            
                                            // 3) finding NET Deductions
                                            netDeduction_20 = grossDeduction - weeklyTaxCrdits;
                                            
                                            // 4) finding NET PAY AT 
                                            NET_PAY = monthlyGrossPay - totalDeduction;
                                            System.out.println("You are not at (Emergency Tax) by " + companyName[i] + "\n");
                                    }
                                        else{
                                            System.out.println("SOMETHING WENT WRONG!!!");
                                        } 
                                    break;
                                    
                                default: // do nothing
                            }
                          }
                        }

                       
                       
                       
                       
                       
                       
                       

                       /**
                        * 
                        * @return 
                        */
                       public double weeklyTaxCredits(){

                            double weeklyTaxCrdits = this.yearlyTaxCredits / this.yearlyWeeks;

                            String taxCredits="Weekly Tax Credits \u20ac " + df.format(weeklyTaxCrdits);
                            System.out.println(taxCredits);

                       return weeklyTaxCrdits;
                       }


                       /**
                        * 
                        * @return 
                        */
                       public double grossPayLimit(){

                            double grossPayLimit = this.rateBand1 / this.yearlyWeeks;

                            String grossLimit ="Weekly Gross pay limit \u20ac " + df.format(grossPayLimit);
                            System.out.println(grossLimit);

                        return grossPayLimit;
                       }


                       /**
                        * 
                        * @return 
                        */
                       public double emergencyTax() throws IOException{

                            double grossDeduction =0;
                            double weeklyGrossPay =0;

                            System.out.println("Please Enter your Weekly Groos pay for :"); 
                            weeklyGrossPay = Double.parseDouble(myKeyboard.readLine());

                            grossDeduction = weeklyGrossPay / this.percentage * this.regularTaxPercentage;
                            System.out.println("Gross duduction \u20ac " + df.format(grossDeduction));

                        return grossDeduction;
                       }
                       
                       
                       
                       
                       
                       
                             /*  // finding out how much the user are getting paid 
                               salaryPerHour = weeklyGrossPay / hoursPerWeek;
                               System.out.println("Your Gross rate of pay for " + companyName[i] + 
                                       " is: \u20ac " + df.format(salaryPerHour) + " Per hour" + "\n"); */

                              /* 
                               // finding the weekly tax credits
                                weeklyTaxCrdits = this.yearlyTaxCredits / this.yearlyWeeks;
                                System.out.println("Weekly Tax Credits \u20ac " + df.format(weeklyTaxCrdits));



                               // finding the deductions based on the weekly gross pay and tax percentage RATE BAND 1
                                 grossDeduction = weeklyGrossPay / this.percentage * this.weeklyTaxPercentage;
                                 System.out.println("Gross Duduction \u20ac " + df.format(grossDeduction));

                               // finding net Deduction
                               netDeduction = grossDeduction - weeklyTaxCrdits;
                               System.out.println("Net Deduction \u20ac " + df.format(netDeduction));

                               // finding net pay
                               double netPay = weeklyGrossPay - netDeduction;
                               System.out.println("Your Net Pay is : \u20ac " + df.format(netPay) );
                       
                        /*  // Getting Tax Credits by dividing each employers credits for yearly weeks 52
                            System.out.println("Your Tax Credits for " + companyName[i] + " is: \u20ac " 
                               + (employersTaxCredit[i] / this.yearlyWeeks) + " per payment" + "\n"); */
                    }

import java.util.*;
class BurgerShop{
	static String orderIdArray[]={};
	static String customerIdArray[]={};
	static String customerNameArray[]={};
	static String orderStatusArray[]={};
	static int burgerQtyArray[]={};
	static double burgerPriceArray[]={};
	final static double BURGERPRICE=500;
	final static int PREPARING = 0;
	final static int DELIVERED = 1;
	final static int CANCELLED = 2;
	
	
	public static void main(String args[]){
		
		burgerShopContent();
	}
	
	//-------------------BURGER SHOP SYSTEM CONTENT----------------
	
	public static void burgerShopContent(){
		clearConsole();
		Scanner input = new Scanner(System.in);
		System.out.println("+----------------------------------------------------+");
		System.out.println("|                   iHungry Burger                   |");
		System.out.println("+----------------------------------------------------+");
		
		System.out.print("\n[1] Place Order");
		System.out.print("\t\t[2] Search Best Customer");
		System.out.print("\n[3] Search Order");
		System.out.print("\t[4] Search Customer");
		System.out.print("\n[5] View Orders");
		System.out.print("\t\t[6] Update Order Details");
		System.out.print("\n[7] Exit");
		
		System.out.print("\n\nEnter an option : ");
		int option=input.nextInt();
		
		switch(option){
			case 1 :
				placeOrder();
			break;
			
			case 2 :
				bestCustomer();
			break;
			
			case 3:
				searchorder();
			break;
			
			case 4:
				customerDetails();
			break;
			
			case 5:
				viewOrders();
			break;
			
			case 6:
				updateOrderDetails();
			break;
			
			case 7:
				clearConsole(); 
				System.out.println("\n\t\tYou left the program...\n"); 
				System.exit(0);
			break;
			default :
				System.out.println("\n\tInvalid option");
				System.out.print("\nDo you want to try entering another valid option : ");
				char ch=input.next().charAt(0);
				
				if(ch=='y' || ch=='Y'){
					burgerShopContent();
				}else if(ch=='n' || ch=='N'){
					clearConsole(); 
					System.out.println("\n\t\tYou left the program...\n"); 
					System.exit(0);
				}
		}
	}
	
	
	//--------------------------OPTION[1]--PLACE ORDER------------------
	
	
	public static void placeOrder(){
		clearConsole();
		System.out.println("+----------------------------------------------------+");
		System.out.println("|                     PLACE ORDER                    |");
		System.out.println("+----------------------------------------------------+");
		
		
		generateOrderId();
		customerIdName();
		burgerQtyPrice();
		
	
	}
	
	//-----------GENERATE ORDERID--------------	
	
	public static void generateOrderId(){
		
			if(orderIdArray.length==0){
				String tempIdArray[]=new String[orderIdArray.length+1];
				tempIdArray[0]="B0001";
				orderIdArray=tempIdArray;
				System.out.println("\n\nORDER ID - "+orderIdArray[0]);
			}else{
				String tempIdArray[]=new String[orderIdArray.length+1];
				for (int i = 0;i < orderIdArray.length; i++)
				{
					tempIdArray[i]=orderIdArray[i];
				}

				tempIdArray[tempIdArray.length-1]="B"+String.format("%04d",Integer.parseInt(orderIdArray[orderIdArray.length-1].substring(1))+1);
 				orderIdArray=tempIdArray;
				System.out.println("\n\nORDER ID - "+orderIdArray[orderIdArray.length-1]);
			}
			System.out.println("================");
	}
	
	//----------CUSTOMER-ID,NAME---------------
	
	public static void customerIdName(){
		Scanner input=new Scanner (System.in);
		L1:	do{
				System.out.print("\n\nEnter the customer id : ");
				String custId=input.next();
				
				if(!isValidCustomerId(custId)){
					System.out.println("Invalid");
					continue L1;
				}
				String tempCustomerId[]=new String[customerIdArray.length+1];
				for (int i = 0; i <customerIdArray.length ; i++)
				{
					tempCustomerId[i]=customerIdArray[i];
				}
				customerIdArray=tempCustomerId;
				customerIdArray[customerIdArray.length-1]=custId;
				
			
				int index=searchCustomerId(custId);
				if(index!=-1){
					String  tempCustName[]=new String[customerNameArray.length+1];
					for(int i=0;i<customerNameArray.length;i++){
						tempCustName[i]=customerNameArray[i];
					}
					customerNameArray=tempCustName;
					customerNameArray[customerNameArray.length-1]=customerNameArray[index];
					System.out.println("Customer Name - "+customerNameArray[index]);
				}else{
					
					System.out.print("Customer Name - ");
					String custname=input.next();
					String  tempCustName[]=new String[customerNameArray.length+1];
					for(int i=0; i<customerNameArray.length ;i++){
						tempCustName[i]=customerNameArray[i];
					}
					customerNameArray=tempCustName;
					customerNameArray[customerNameArray.length-1]=custname;
						
				}
				break L1;
			}while(true);	
	}
	
	
	//------------VALIDATION OF CUSTOMER-ID------------
	
	
	public static boolean isValidCustomerId(String customerId){
		if(customerId.charAt(0)=='0' && customerId.length()==10){
			return true;
		}
		return false;
	}
	
	//------------SEARCH CUSTOMER-ID------------
	
	
	public static int searchCustomerId(String custId){
		for (int i = 0; i <customerIdArray.length-1; i++)
		{
			if(custId.equalsIgnoreCase(customerIdArray[i])){
				return i;
			}
		}
		return -1;
	}
	
	
	//-------------BURGER QTY & PRICE------------------
	
	public static void burgerQtyPrice(){
		Scanner input=new Scanner(System.in);
		
		System.out.print("Enter the burger qauntity - ");
		int burgers = input.nextInt();
		
		System.out.printf("Total value - %.2f",(burgers*BURGERPRICE));
		if(!confirmingOrder()){
			System.out.println("\n\tThis order has been cancelled....");
			RemoveData();
		}else{
			System.out.println("\n\tYour order is successfully entered into the system....");
			int tempBurgerQty[] =new int[burgerQtyArray.length+1];
			String tempStatus[] =new String[orderStatusArray.length+1];
			for (int i = 0; i <burgerQtyArray.length; i++)
			{
				tempBurgerQty[i]=burgerQtyArray[i];
				tempStatus[i] = orderStatusArray[i];
			}
			burgerQtyArray=tempBurgerQty;
			burgerQtyArray[burgerQtyArray.length-1] = burgers;
			
			orderStatusArray=tempStatus;
			orderStatusArray[orderStatusArray.length-1] ="PREPARING";
			
			double tempBurgerPrice[] = new double[burgerPriceArray.length+1];
			for (int j = 0; j <burgerPriceArray.length ; j++)
			{
				tempBurgerPrice[j]= burgerPriceArray[j];
			}
			burgerPriceArray=tempBurgerPrice;
			burgerPriceArray[burgerPriceArray.length-1]= burgers*BURGERPRICE;
			
		}
		
		L6:do{
			System.out.print("\nDo you want to place another order(Y/N) : ");
			char ch=input.next().charAt(0);
				
			if(!(ch=='y' || ch=='Y' || ch=='n' || ch=='N')){
				System.out.println("Invalid input....");
				continue L6;
			}
			if(ch=='y' || ch=='Y'){
				placeOrder();
			}else if(ch=='n' || ch=='N'){
				burgerShopContent();
			}
			break;
		}while(true);
		
		
	}
	
	//---------------REMOVING THE CANCELED ORDER DATA-----------------
	
	public static void RemoveData(){
		String tempOrderId[]=new String[orderIdArray.length-1];	
		String tempCustId[]=new String[customerIdArray.length-1];	
		String tempCustName[]=new String[customerNameArray.length-1];
		for (int i = 0; i <tempOrderId.length; i++)
		{
			tempOrderId[i]=orderIdArray[i];
			tempCustId[i]=customerIdArray[i];
			tempCustName[i]=customerNameArray[i];
		}
		orderIdArray=tempOrderId;
		customerIdArray=tempCustId;
		customerNameArray=tempCustName;
	}
	
	//--------------CONFIRMATION OF ORDER-----------------
	
	public static boolean confirmingOrder(){
		Scanner input=new Scanner(System.in);
		L2:	do{
				System.out.print("\n\n\tAre you confirming your order(Y/N) : ");
				char ch = input.next().charAt(0);
				
				if(!(ch=='Y' || ch=='y' || ch=='N' || ch=='n')){
					System.out.println("Invalid input");
					continue L2;
				}
				if(ch=='Y' || ch=='y'){
					return true;
				}
				
				break L2;
			}while(true);
			return false;
	}
	
	
	//=====================================================================//
	//------------Option 2 BEST CUSTOMER---------------
	
	public static void bestCustomer(){
		clearConsole();
		Scanner input=new Scanner(System.in);
		System.out.println("+----------------------------------------------------+");
		System.out.println("|                    BEST CUSTOMER                   |");
		System.out.println("+----------------------------------------------------+");
		
		
		String dupRemId[]={};
		String dupRemName[]={};
		
		for (int i = 0; i <customerIdArray.length; i++)
		{
			if(!searchDupRem(customerIdArray[i],dupRemId)){
				String tempId[]=new String[dupRemId.length+1];
				String tempName[]=new String[dupRemName.length+1];
				
				for (int j = 0; j <dupRemId.length; j++)
				{
					tempId[j]=dupRemId[j];
					tempName[j]=dupRemName[j];
				}
				dupRemName=tempName;
				dupRemName[dupRemName.length-1]=customerNameArray[i];
				dupRemId=tempId;
				dupRemId[dupRemId.length-1]=customerIdArray[i];
			}
		}
		
		//--------calculate totals of duplicates & non duplicates-----------
		double total[]=new double[dupRemId.length];
		
		for (int i = 0; i <total.length; i++)
		{
			double tot=0;
			for (int j = 0; j <customerIdArray.length; j++)
				{
					if(dupRemId[i].equalsIgnoreCase(customerIdArray[j])){
						if(!(orderStatusArray[j].equals("CANCELLED"))){
							tot+=burgerPriceArray[j];
						}else{
							tot+=0;
						}
					}
				}
				total[i]=tot;
		}
		
		//----------------SORTING THE TOTAL------------------
		System.out.println("\n\n--------------------------------------------");
		System.out.printf("%-15s %-20s %-10s\n", "Customer Id", "Customer Name", "Total");
		
		
		
		for (int i = total.length-1; i >= 0; i--)
		{
			double max=0;
			String tempName="";
			for (int j = 0; j <i ; j++)
			{
				if(total[j]>total[j+1]){
					max=total[j];
					total[j]=total[j+1];
					total[j+1]=max;
					
					tempName=dupRemName[j];
					dupRemName[j] = dupRemName[j+1];
					dupRemName[j+1] = tempName;
				}
			}
			System.out.println("--------------------------------------------");
			System.out.printf("%-15s %-20s %-10s\n", dupRemId[i], dupRemName[i], total[i]);
		}
		System.out.println("--------------------------------------------");
		
		L7:do{
			System.out.print("\t\nDo you want to home page (Y/N) :  ");
			char ch=input.next().charAt(0);
			
			if(!(ch=='y' || ch=='Y' || ch=='n' || ch=='N')){
				System.out.println("Invalid input....");
				continue L7;
			}
			if(ch=='y' || ch=='Y'){
				burgerShopContent();
			}else if(ch=='n' || ch=='N'){
				clearConsole(); 
				System.out.println("\n\t\tYou left the program...\n"); 
				System.exit(0);
			}
			break;
		}while(true);
	}
	
	//-----------SEARCH OF DUPREM ARRAY OF THE ORDER ID----------------
	public static boolean searchDupRem(String Id,String duprem[]){
		for (int i = 0; i <duprem.length; i++)
		{
			if(Id.equalsIgnoreCase(duprem[i])){
				return true;
			}
		}
		return false;
	}
	
	
	
	//=====================================================================//
	//------------Option 3 Search Order---------------
	
	public static void searchorder(){
		clearConsole();
		Scanner input=new Scanner(System.in);
		System.out.println("+----------------------------------------------------+");
		System.out.println("|                     SEARCH ORDER                   |");
		System.out.println("+----------------------------------------------------+");
		
		L3:	do{
				System.out.print("Enter the order Id : ");
				String  orderID=input.next();
				
				int index = searchOrderId(orderID);
				if(index==-1){
					System.out.println("Invalid Order Id.");
					continue L3;
				}else{
					System.out.println("+--------------------------------------------------------------------------------+");
					System.out.printf("| %-10s %-12s %-15s %-10s %-13s %-15s |\n", 
									  "Order Id", "Customer Id", "Name", "Quantity", "Order Value", "Order Status");
					System.out.println("+--------------------------------------------------------------------------------+");

					// Print order details
					System.out.printf("| %-10s %-12s %-15s %-10d %-13.2f %-15s |\n", 
									  orderIdArray[index], 
									  customerIdArray[index], 
									  customerNameArray[index], 
									  burgerQtyArray[index], 
									  burgerPriceArray[index], 
									  orderStatusArray[index]);
					System.out.println("+--------------------------------------------------------------------------------+");

					
				}
				break;
			}while(true);
			O1:do{
				System.out.print("\nDo you want to try searching the order details again : ");
				char ch=input.next().charAt(0);
				
				if(!((ch=='Y') || (ch=='y') || (ch=='N') || (ch=='n'))){
					System.out.println("WRONG input. Try again");
					continue O1;
				}else
				if(ch=='y' || ch=='Y'){
					searchorder();
				}else if(ch=='n' || ch=='N'){
					burgerShopContent();
				}
				break;
			}while(true);
				
				
			
	}
	
	//-----------------IS ORDER ID (USED FOR OPTION (3) & (6)----------------
		
	public static int searchOrderId(String orderID){
		for (int i = 0; i < orderIdArray.length; i++)
		{
			if(orderID.equals(orderIdArray[i])){
				return i;
			}
		}
		return -1;
	}
	
	
	
	
	//=====================================================================//
	//----------------OPTION(4) Search customer details----------------------------
	
	public static void customerDetails(){
		clearConsole();
		Scanner input=new Scanner(System.in);
		System.out.println("+----------------------------------------------------+");
		System.out.println("|                SEARCH CUSTOMER DETAILS             |");
		System.out.println("+----------------------------------------------------+");
		
		System.out.print("\nEnter customer Id : ");
		String custId = input.next();
		
		int index=custIdSearch(custId);
		
		if(index == -1){
			System.out.println("\n\n\tThis ccustomer Id has note been added to system....");
		}else{
				
			System.out.println("\n\nCustomer Od   - "+customerIdArray[index]);
			System.out.println("Customer Name - "+customerNameArray[index]);
			
			System.out.println("\nCustomer Order Details");
			System.out.println("======================");
			
			System.out.println("\n------------------------------------------------");
			System.out.printf("%-10s %-18s %-12s","Order_Id","Order_Qauntity","Total_Value");
			System.out.println("\n------------------------------------------------");
			
			for (int i = 0; i < customerIdArray.length; i++)
			{
				if(customerIdArray[i].equals(custId)){
					System.out.printf("\n%-10s %-18s %-12s",orderIdArray[i],burgerQtyArray[i],burgerPriceArray[i]);
				}
			}
			System.out.println("\n------------------------------------------------");
			
		}
		
		System.out.print("\nDo you want to search another custoner's details (Y/N) : ");	
		char ch=input.next().charAt(0);
			
		if(ch=='y' || ch=='Y'){
			customerDetails();
		}else if(ch=='n' || ch=='N'){
			burgerShopContent();
		}
		
	}
	
	//----------------CUST ID SEARCH------------------
	
	public static int custIdSearch(String custId){
		for (int i = 0; i < customerIdArray.length; i++)
		{
			if(customerIdArray[i].equals(custId)){
				return i;
			}
		}
		return -1;
	}
	
	
	//=====================================================================//
	//----------------OPTION(5) VIEW ORDER LIST----------------------------
	
	public static void viewOrders(){
		clearConsole();
		Scanner input=new Scanner(System.in);
		System.out.println("+----------------------------------------------------+");
		System.out.println("|                   VIEW ORDER LIST                  |");
		System.out.println("+----------------------------------------------------+");
		
		System.out.println("\n[1] Delivered Order");
		System.out.println("[2] PREPARING Order");
		System.out.println("[3] CANCELLED Order");
		
		System.out.print("\nEnter an option to continue : ");
		int option = input.nextInt();
		
		switch(option)
		{
			case 1:
			{
				deliveredOrders();
				System.out.print("\nDo you want to home page (Y/N) :  ");
				char ch=input.next().charAt(0);
					
				if(ch=='y' || ch=='Y'){
					viewOrders();
				}else if(ch=='n' || ch=='N'){
					burgerShopContent();
				}
			}
			break;
			case 2:{
				preparingOrders();
				System.out.print("\nDo you want to home page (Y/N) :  ");
				char ch=input.next().charAt(0);
					
				if(ch=='y' || ch=='Y'){
					viewOrders();
				}else if(ch=='n' || ch=='N'){
					burgerShopContent();
				}
			}
			break;
			case 3:
			{
				cancelledOrders();
				System.out.print("\nDo you want to home page (Y/N) :  ");
				char ch=input.next().charAt(0);
					
				if(ch=='y' || ch=='Y'){
					viewOrders();
				}else if(ch=='n' || ch=='N'){
					burgerShopContent();
				}
			}
			break;
			
			default :{
				System.out.println("Invalid option...");
			}
		}		
		
		System.out.print("\nDo you want to try entering another valid option : ");
		char ch=input.next().charAt(0);
			
		if(ch=='y' || ch=='Y'){
			viewOrders();
		}else if(ch=='n' || ch=='N'){
			burgerShopContent();
		}
	}
	
	//-----------DELIVERED ORDERS----------------
	
	public static void deliveredOrders(){
		clearConsole();
		System.out.println("+----------------------------------------------------+");
		System.out.println("|                  DELIVERED ORDERS                  |");
		System.out.println("+----------------------------------------------------+");
		
		System.out.println("\n\n--------------------------------------------------------------");
		System.out.printf("%-10s %-13s %-12s %-10s %-12s%n", "Order Id", "Customer Id", "Name", "Quantity", "Order Price");
		for (int i = 0; i < orderStatusArray.length ; i++)
		{
			if("DELIVERED".equals(orderStatusArray[i])){
				System.out.println("--------------------------------------------------------------");
				System.out.printf("%-10s %-13s %-12s %-10d %-12.2f%n",
				orderIdArray[i],
				customerIdArray[i],
				customerNameArray[i],
				burgerQtyArray[i],
				burgerPriceArray[i]);

			}
			
		}
		System.out.println("--------------------------------------------------------------");
		//System.out.println("\nDO you want to home page (Y/N) :  ");
		
	}
	
	//-----------OREPARING ORDERS----------------
	
	public static void preparingOrders(){
		clearConsole();
		System.out.println("+----------------------------------------------------+");
		System.out.println("|                  PREPARING ORDERS                  |");
		System.out.println("+----------------------------------------------------+");
		
		System.out.println("\n\n--------------------------------------------------------------");
		System.out.printf("%-10s %-13s %-12s %-10s %-12s%n", "Order Id", "Customer Id", "Name", "Quantity", "Order Price");
		for (int i = 0; i < orderStatusArray.length ; i++)
		{
			if("PREPARING".equals(orderStatusArray[i])){
				System.out.println("--------------------------------------------------------------");
				System.out.printf("%-10s %-13s %-12s %-10d %-12.2f%n",
				orderIdArray[i],
				customerIdArray[i],
				customerNameArray[i],
				burgerQtyArray[i],
				burgerPriceArray[i]);

			}
			
		}
		System.out.println("--------------------------------------------------------------");
	}
	
	//-----------CANCELLED ORDERS----------------
	
	public static void cancelledOrders(){
		clearConsole();
		System.out.println("+----------------------------------------------------+");
		System.out.println("|                  CANCELLED ORDERS                  |");
		System.out.println("+----------------------------------------------------+");
		
		System.out.println("\n\n--------------------------------------------------------------");
		System.out.printf("%-10s %-13s %-12s %-10s %-12s%n", "Order Id", "Customer Id", "Name", "Quantity", "Order Price");
		for (int i = 0; i < orderStatusArray.length ; i++)
		{
			if("CANCELLED".equals(orderStatusArray[i])){
				System.out.println("--------------------------------------------------------------");
				System.out.printf("%-10s %-13s %-12s %-10d %-12.2f%n",
				orderIdArray[i],
				customerIdArray[i],
				customerNameArray[i],
				burgerQtyArray[i],
				burgerPriceArray[i]);

			}
			
		}
		System.out.println("--------------------------------------------------------------");
	}
	
	
	//=====================================================================//
	//----------------OPTION(6) UPDATE ORDER DETAILS----------------------------
	
	public static void updateOrderDetails(){
		clearConsole();
		Scanner input=new Scanner(System.in);
		System.out.println("+-------------------------------------------------------------------+");
		System.out.println("|                        UPDATE ORDER DETAILS                       |");
		System.out.println("+-------------------------------------------------------------------+");
		
		System.out.print("\nEnter the Order Id : ");
		String orderId =input.next();
		int index=searchOrderId(orderId);
		if(index==-1){
			System.out.println("\nSorry,there is no any order that haas been placed by this Order Id."); 
		}else{
			if(orderStatusArray[index]=="DELIVERED"){
				System.out.println("\nSorry ,this order has been delivered you cannot update this order....");
			}else if(orderStatusArray[index]=="CANCELLED"){
				System.out.println("\nSorry ,this order has been cancelled you cannot do any changes this order.....");
			}else{
				System.out.println("\nOrder Id     -"+orderIdArray[index]);
				System.out.println("Customer Id  -"+customerIdArray[index]);
				System.out.println("Name         -"+customerNameArray[index]);
				System.out.println("Qauntity     -"+burgerQtyArray[index]);
				System.out.println("Order Price  -"+burgerPriceArray[index]);
				System.out.println("Order status -"+orderStatusArray[index]);
				
				System.out.println("\nWhat do you want to update ?");
				System.out.println("\t[1] Qauntity");
				System.out.println("\t[2] Status");
				
				L4:do{		
					System.out.print("\nEnter an option : ");
					int option=input.nextInt();
					
					if(option==1){
						qauntityUpadate(index);
					}else if(option==2){
						statusUpdate(index);
					}else{
						System.out.println("Invalid option");
						continue L4;
					}
					break;
				}while(true);	
			}
			
		}
		System.out.print("\n\nDo you want to try again updating the orders(Y/N) : ");
		char ch =input.next().charAt(0);
		
		if(ch=='y' || ch=='Y'){
				updateOrderDetails();
			}else if(ch=='n' || ch=='N'){
				burgerShopContent();
			}
		
	}
	
	//----------------Qauntity Update-----------------------
	
	public static void qauntityUpadate(int index){
		clearConsole();
		Scanner input=new Scanner(System.in);
		System.out.println("Qauntity Update");
		System.out.println("===============");
		
		System.out.println("\nOrder Id - "+orderIdArray[index]+"\nCustomer Id - "+customerIdArray[index]+"\nName - "+customerNameArray[index]);
		System.out.print("\nEnter your new qauntity : ");
		int burgerQty=input.nextInt();
		
		burgerQtyArray[index] = burgerQty;
		burgerPriceArray[index]=burgerQty*BURGERPRICE;
		
		System.out.println("\n\tUpdating order qauntity succcessfully....");
		System.out.println("\nNew order qauntity : "+burgerQty);
		System.out.println("New price of order "+orderIdArray[index]+" : "+burgerPriceArray[index]);
		
		//System.out.println(Arrays.toString(burgerPriceArray));
		//System.out.println(Arrays.toString(burgerQtyArray));
		
		
	}
		
	//----------------Status Update-----------------------
	
	public static void statusUpdate(int index){
		clearConsole();
		Scanner input=new Scanner(System.in);
		System.out.println("Status Update");
		System.out.println("=============");
		
		System.out.println("\nOrder Id "+orderIdArray[index]+"\nCustomer Id - "+customerIdArray[index]+"\nName - "+customerNameArray[index]);
		System.out.println("\n\t[1] CANCEL");
		System.out.println("\t[2] PREPARING");
		System.out.println("\t[3] DELIVERED");
		
		L5:do{
			System.out.print("\nEnter the new status of this order : ");
			int status=input.nextInt();
			
			if(status==1){
				orderStatusArray[index]="CANCELLED";
			}else if(status==2){
				orderStatusArray[index]="PREPARING";
			}else if(status==3){
				orderStatusArray[index]="DELIVERED";
			}else{
				System.out.println("Invalid input");
				continue L5;
			}
			
			System.out.println("\n\tUpdating order status successfully....");
			
			System.out.println("\nNew order status : "+orderStatusArray[index]);
			break;
		}while(true);
	}
			
		
		
		
		
		
	//-------------CLEAR-CONSOLE METHOD--------------
	
	public final static void clearConsole() { 
		try { 
			final String os = 
			System.getProperty("os.name"); if 
			(os.contains("Windows")) { 
				new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor(); 
			} else { 
				System.out.print("\033[H\033[2J"); 
				System.out.flush(); 
			} 
		} catch (final Exception e) {
			e.printStackTrace();
			// Handle any exceptions.
		} 
	}
}
		
	

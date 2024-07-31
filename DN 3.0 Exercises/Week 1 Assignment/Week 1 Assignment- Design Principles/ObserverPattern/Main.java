
public class Main {
    public static void main(String[] args) {
        StockMarket appleStock = new StockMarket("AAPL", 150.00);

        Observer mobileApp = new MobileApp("Stock Mobile App");
        Observer webApp = new WebApp("Stock Web App");

        appleStock.registerObserver(mobileApp);
        appleStock.registerObserver(webApp);

        appleStock.setStockPrice(155.00);
        appleStock.setStockPrice(160.00);


        appleStock.removeObserver(mobileApp);
        appleStock.setStockPrice(165.00);
    }
}

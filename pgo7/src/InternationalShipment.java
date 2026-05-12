public class InternationalShipment extends ShipmentOrder{
    private String destinationCountry;
    private boolean customDocumentsRequired;
    private boolean expressDelivery;
    public InternationalShipment(String orderNumber, String customerName
            , double distanceKm, double baseFee, boolean insured, String destinationCountry, boolean customDocumentsRequired
            , boolean expressDelivery){
        super(orderNumber, customerName, distanceKm, baseFee, insured);
        this.destinationCountry = destinationCountry;
        this.expressDelivery = expressDelivery;
        this.customDocumentsRequired = customDocumentsRequired;
    }

    @Override
    public String getShipmentType(){
        return "International";
    }

    @Override
    protected double calculateBasePrice(){
        return getBaseFee() + getDistanceKm() * 2.10;
    }

    @Override
    protected double calculateAdditionalFee(){
        double fee = 0;
        if (customDocumentsRequired){
            fee += 45;
        }
        if (expressDelivery){
            fee += 80;
        }
        return fee;
    }

    @Override
    protected void validateSpecificRules(){
        if (destinationCountry == null || destinationCountry.isEmpty()){
            throw new IllegalArgumentException("Destination country is empty");
        }
    }

    @Override
    protected double applyBusinessDiscount(double price){
        if (!expressDelivery && getDistanceKm() > 1000){
            price = price * 0.97;
        }
        return price;
    }
}

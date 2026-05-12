public class PickupPointShipment extends ShipmentOrder{
    private String locketSize;
    private boolean fragile;
    public PickupPointShipment(String orderNumber, String customerName
            , double distanceKm, double baseFee, boolean insured, String locketSize, boolean fragile){
        super(orderNumber, customerName, distanceKm, baseFee, insured);
        this.locketSize = locketSize;
        this.fragile = fragile;
    }

    @Override
    public String getShipmentType() {
        return "Pickup Point";
    }

    @Override
    protected double calculateBasePrice(){
        return getBaseFee() + getDistanceKm() * 0.75;
    }

    @Override
    protected double calculateAdditionalFee(){
        double fee = 0;
        switch(locketSize){
            case "S":
                fee += 5;
                break;
                case "M":
                    fee += 10;
                    break;
                    case "L":
                        fee += 18;
                        break;
        }
        if(fragile){
            fee += 12;
        }
        return fee;
    }

    @Override
    protected void validateSpecificRules(){
        if(!locketSize.equals("S")
        && !locketSize.equals("M")
        && !locketSize.equals("L")){
            throw new IllegalArgumentException("Invalid locket size");
        }
    }
}

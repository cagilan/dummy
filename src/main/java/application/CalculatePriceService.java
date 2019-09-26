package application;

import model.CalculatedPrice;
import model.EventProductRequest;
import org.springframework.stereotype.Service;


@Service
public class CalculatePriceService {

    public CalculatedPrice getPriceDetails(EventProductRequest eventProductRequest) {

        CalculatedPrice calculatedPrice = new CalculatedPrice();
        calculatedPrice.setSku(eventProductRequest.getSku());
        try {
            if (eventProductRequest != null && eventProductRequest.getOptions() != null) {
                JSONMapperUtil.loadProductPriceDetails();
                int copies = eventProductRequest.getOptions().getCopies();
                double totalMaterialPrice = calculateMaterialPrice(eventProductRequest.getOptions().getMaterial(), copies);
                double totalDrillingPrice = calculateDrillingPrice(eventProductRequest.getOptions().getDrilling_holes(), copies);
                double totalFinishingPrice = calculateFinishingPrice(eventProductRequest.getOptions().getFinish(), copies);
                int sizePrice = (int) JSONMapperUtil.sizesMap.get(eventProductRequest.getOptions().getSize());
                calculatedPrice.setPrice(sizePrice + totalMaterialPrice + totalDrillingPrice + totalFinishingPrice);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return calculatedPrice;
    }


    private double calculateMaterialPrice(String materialType, int copies) {
        if (materialType != null && copies != 0) {
            double price = JSONMapperUtil.materialPriceMap.get(materialType) != null ? (Double) JSONMapperUtil.materialPriceMap.get(materialType) : 0;
            double materialPrice = price != 0 ? price * copies / 1000 : 0;
            return materialPrice;
        }
        return 0;
    }

    private double calculateDrillingPrice(int drillingType, int copies) {
        if (drillingType != 0 && copies != 0) {
            int price = JSONMapperUtil.drillingPriceMap.get(drillingType) != null ? (Integer) JSONMapperUtil.drillingPriceMap.get(drillingType) : 0;
            double drillingPrice = price != 0 ? price * copies / 1000 : 0;
            return drillingPrice;
        }
        return 0;
    }

    private double calculateFinishingPrice(String finishType, int copies) {
        if (finishType != null && copies != 0) {
            double price = JSONMapperUtil.finishingPriceMap.get(finishType) != null ? (Double) JSONMapperUtil.finishingPriceMap.get(finishType) : 0;
            double finishingPrice = price != 0 ? price * copies / 1000 : 0;
            return finishingPrice;
        }
        return 0;
    }
}

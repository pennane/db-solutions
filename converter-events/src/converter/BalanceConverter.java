package converter;

import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

@Converter
public class BalanceConverter implements AttributeConverter<Double, Integer> {
    @Override
    public Integer convertToDatabaseColumn(Double balance) {
        if (balance == null) {
            return null;
        }
        return (int) Math.round(balance * 100);
    }

    @Override
    public Double convertToEntityAttribute(Integer balanceInCents) {
        if (balanceInCents == null) {
            return null;
        }
        return balanceInCents / 100.0;
    }
}

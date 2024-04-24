
package Models;

import java.util.LinkedHashMap;
import java.util.Map;

public class Discount {

    private boolean enabled;
    private String discountType;
    private double discountValue;
    private Map<String, Object> additionalProperties = new LinkedHashMap<String, Object>();

    /**
     * No args constructor for use in serialization
     *
     */
    public Discount() {
    }

    /**
     *
     * @param discountType
     * @param discountValue
     * @param enabled
     */
    public Discount(boolean enabled, String discountType, double discountValue) {
        super();
        this.enabled = enabled;
        this.discountType = discountType;
        this.discountValue = discountValue;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    public String getDiscountType() {
        return discountType;
    }

    public void setDiscountType(String discountType) {
        this.discountType = discountType;
    }

    public double getDiscountValue() {
        return discountValue;
    }

    public void setDiscountValue(double discountValue) {
        this.discountValue = discountValue;
    }

    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(Discount.class.getName()).append('@').append(Integer.toHexString(System.identityHashCode(this))).append('[');
        sb.append("enabled");
        sb.append('=');
        sb.append(this.enabled);
        sb.append(',');
        sb.append("discountType");
        sb.append('=');
        sb.append(((this.discountType == null)?"<null>":this.discountType));
        sb.append(',');
        sb.append("discountValue");
        sb.append('=');
        sb.append(this.discountValue);
        sb.append(',');
        sb.append("additionalProperties");
        sb.append('=');
        sb.append(((this.additionalProperties == null)?"<null>":this.additionalProperties));
        sb.append(',');
        if (sb.charAt((sb.length()- 1)) == ',') {
            sb.setCharAt((sb.length()- 1), ']');
        } else {
            sb.append(']');
        }
        return sb.toString();
    }

}

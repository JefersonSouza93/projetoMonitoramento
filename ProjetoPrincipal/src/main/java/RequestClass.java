public class RequestClass {
    double latitudeMin;
    double latitudeMax;
    double longitudeMin;
    double longitudeMax;

    public double getlatitudeMin() {
        return latitudeMin;
    }

    public void setlatitudeMin(double latitudeMin) {
        this.latitudeMin = latitudeMin;
    }

    public double getlatitudeMax() {
        return latitudeMax;
    }

    public void setlatitudeMax(double latitudeMax) {
        this.latitudeMax = latitudeMax;
    }

    public double getLongitudeMin() {
        return longitudeMin;
    }

    public void setLongitudeMin(double longitudeMin) {
        this.longitudeMin = longitudeMin;
    }

    public double getLongitudeMax() {
        return longitudeMax;
    }

    public void setLongitudeMax(double longitudeMax) {
        this.longitudeMax = longitudeMax;
    }

    public RequestClass(double latitudeMin, double latitudeMax,
                        double longitudeMin, double longitudeMax) {
        this.latitudeMin = latitudeMin;
        this.latitudeMax = latitudeMax;
        this.longitudeMin = longitudeMin;
        this.longitudeMax = longitudeMax;
    }

    public RequestClass() {
    }
}
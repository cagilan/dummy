package model;

public class Options {

    int copies;
    String size;
    String material;
    String finish;
    int drilling_holes;
    int bundles;

    public int getCopies() {
        return copies;
    }

    public void setCopies(int copies) {
        this.copies = copies;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public String getMaterial() {
        return material;
    }

    public void setMaterial(String material) {
        this.material = material;
    }

    public String getFinish() {
        return finish;
    }

    public void setFinish(String finish) {
        this.finish = finish;
    }

    public int getDrilling_holes() {
        return drilling_holes;
    }

    public void setDrilling_holes(int drilling_holes) {
        this.drilling_holes = drilling_holes;
    }

    public int getBundles() {
        return bundles;
    }

    public void setBundles(int bundles) {
        this.bundles = bundles;
    }
}

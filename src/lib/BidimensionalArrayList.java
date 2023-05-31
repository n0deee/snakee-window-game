package lib;

import java.util.ArrayList;

import errors.RuntimeExceptionBidimensionalArrayNegativeSize;

public class BidimensionalArrayList<T> {
    private Vec2 size;
    protected ArrayList<ArrayList<T>> buff;

    public BidimensionalArrayList(Vec2 size, T defaultObj) {
        if (size.x <= 0 && size.y <= 0)
        {
            throw new RuntimeExceptionBidimensionalArrayNegativeSize();
        }
        
        this.size = size;
        this.buff = new ArrayList<ArrayList<T>>();

        for (int y = 0; y < size.y; y++) {
            ArrayList<T> yArr = new ArrayList<T>();
            this.buff.add(yArr);
            for (int x = 0; x < size.x; x++) {
                yArr.add(defaultObj);
            }
        }
    }

    public BidimensionalArrayList(Vec2 size) {
        this(size, null);
    }


    public void set(Vec2 pos, T value) {
        buff.get(pos.y).set(pos.x, value);
    }

    public T get(Vec2 pos) {
        return buff.get(pos.y).get(pos.x);
    }

    public void clear(T value) {
        for (int y = 0; y < this.size().y; y++) {
            for (int x = 0; x < this.size().x; x++) {
                this.set(new Vec2(x, y), value);
            }
        }
    }

    public void clear() {
        this.clear(null);
    }

    public Vec2 size() {
        return this.size;
    }

}

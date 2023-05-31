package game.entities;

import java.util.ArrayList;

import errors.RuntimeExceptionSmallSnake;
import lib.Vec2;

public class Snake extends AbstractEntity {
    private static final int HEAD_INDEX = 0;
    public static final int MIN_LENGHT = 1;
    
    private ArrayList<Vec2> body;
    
    public Snake(Vec2 initalPos, int initalLenght) {
        if (initalLenght < MIN_LENGHT)
            throw new RuntimeExceptionSmallSnake();
        
        this.body = new ArrayList<Vec2>();

        for (int i = 0; i < initalLenght; i++) {
            body.add(initalPos.copy());
        }
    }

    public Vec2 getHeadPos() {
        return body.get(HEAD_INDEX);
    }

    public void setHeadPos(Vec2 pos) {
        body.set(HEAD_INDEX, pos);
    }

    public Vec2 getTailPos() {
        if (body.size() <= 1)
            return null;
            
        return body.get(body.size() - 1);
    }

    public void grow(int ammount) {
        for (int i = 0; i < ammount; i++) {
            body.add(this.getTailPos().copy());
        }
    }

    public void shrink(int ammount) {
        for (int i = 0; i < ammount; i++) {
            if (body.size() <= MIN_LENGHT)
                break;
                
            body.remove(this.body.size() - 1);
        }
    }

    public void bodyStep(Vec2 newHeadPos) {
        for (int i = body.size() - 1; i >= MIN_LENGHT; i--) {
            body.set(i, body.get(i - 1).copy());
        }
        this.setHeadPos(newHeadPos);
    }

    public ArrayList<Vec2> getEntireBody() {
        return this.body;
    }

}

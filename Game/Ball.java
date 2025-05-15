package Game;

import java.util.ArrayList;

public class Ball extends Circly{
    double slow=8.0/0.008;
    double slowX=0;
    double slowY=0;
    double moveAngle;
    static int rtg;
    static int rbg;
    static int ltg;
    static int lbg;

    Ball(){
        xPos= Game.Main.width/2;
        yPos= Game.Main.height/2;
        radius=8;
        size=16;
    }

    void hit(Circly p){
        this.xSpeed*=0.008;
        this.ySpeed*=0.008;

        double ydist=this.yPos-p.yPos;
        double xdist=this.xPos-p.xPos;
        this.xSpeed+=p.xSpeed*3/5;
        this.ySpeed+=p.ySpeed*3/5;
        double speed=Math.sqrt(p.ySpeed*p.ySpeed+p.xSpeed*p.xSpeed)+10;
        double angle=Math.atan2(ydist,xdist);
        this.xSpeed=speed*Math.cos(angle);
        this.ySpeed=speed*Math.sin(angle);
        this.xSpeed+=p.xSpeed*3/5;
        this.ySpeed+=p.ySpeed*3/5;

        this.xSpeed/=0.008;
        this.ySpeed/=0.008;

    }
    void move(){
        if(yPos+radius+getYSpeed()>=780&&ySpeed>0){
            yPos+=780-(yPos+getYSpeed());
            ySpeed=-ySpeed;
        }else if(yPos-radius+getYSpeed()<=30&&ySpeed<0){
            yPos+=30-(yPos+getYSpeed());
            ySpeed=-ySpeed;
        }else yPos+=getYSpeed();
        if(xPos+radius+getXSpeed()>=1145&&!(yPos>=rtg&&yPos<=rbg)&&xSpeed>0){
            xPos+=1145-(xPos+getXSpeed());
            xSpeed=-xSpeed;
        }else if(xPos+radius+getXSpeed()<=70&&!(yPos>=ltg&&yPos<=lbg)&&xSpeed<0){
            xPos+=70-(xPos+getXSpeed());
            xSpeed=-xSpeed;
        }else xPos+=getXSpeed();

        moveAngle=Math.atan2(ySpeed, xSpeed);
        slowX=slow* Game.Main.delta*Math.abs(Math.cos(moveAngle));
        slowY=slow* Game.Main.delta*Math.abs(Math.sin(moveAngle));

        if(xSpeed>=slowX) xSpeed-=slowX;
        else if(xSpeed<=-slowX) xSpeed+=slowX;
        else xSpeed=0;
        if(ySpeed>=slowY) ySpeed-=slowY;
        else if(ySpeed<=-slowY) ySpeed+=slowY;
        else ySpeed=0;
    }

    double getYSpeed(){
        return ySpeed* Game.Main.delta;
    }

    double getXSpeed(){
        return xSpeed* Game.Main.delta;
    }

    private final ArrayList<BallObserver> observers = new ArrayList<>();
    public void addObserver(BallObserver o) {
        observers.add(o);
    }
    void notifyGoalScored(String team) {
        for (BallObserver o : observers) {
            o.onGoalScored(team);
        }
    }



}

import java.util.Random;


public class Main {

    public static void main(String[] args) {
        java.util.Scanner scan = new java.util.Scanner(System.in);

        System.out.println("This is a projectile game. To exit the game, type 'stop' after a round");
        boolean keepGoing = true;
        int points = 5;
        int i = 1;

        while(keepGoing){
            points --;
            double distance = createWall()[0];
            double height = createWall()[1];
            System.out.println("Round " + i +": You have "+ points+" points");
            i ++;
            System.out.format("Wall distance is %.2f%n",distance);
            System.out.format("Wall height is %.2f%n",height);
            System.out.println("What angle (degree) do you want to aim?");
            double angle = scan.nextDouble();
            System.out.println("WHat speed (m/s) do you want to eject?");
            double speed = scan.nextDouble();
            double hitLocation = whereHit(distance,angle,speed);
            double gap = height - hitLocation;
            System.out.format("Your projectile's height is %.2f at wall position %n",hitLocation);
            int pointThisRound = getScore(gap);
            points += pointThisRound;
            System.out.println("You earned "+pointThisRound+" this round and your total points goes to "+points);

            if(points<= 0){
                System.out.println("You don't have enough points to continue");
                break;
            }

            System.out.println("Do you want to play again? (type 'no' to quit)");
            String end = scan.next();
            if (end.equals("no")){
                System.out.println("see you next time");
                keepGoing = false;
            }

        }



    }

    public static double[] createWall(){
        //This function generates random wall position: distance from 10 to 100 and height from 10 to 20
        Random random = new Random();
        double distance = random.nextDouble()*90+10;
        double height = random.nextDouble()*20+10;
        double wallPosition[] = new double[2];
        wallPosition[0] = distance;
        wallPosition[1] = height;
        return wallPosition;
    }

    public static double whereHit(double x,double theta,double v){
        double angle = Math.toRadians(theta);
        double y = x*Math.tan(angle) - 9.8*Math.pow(x,2)/(2*Math.pow(v*Math.cos(theta),2));
        return y;
    }

    public static double whereMeet(double distance, double theta, double vProjectile){
        Random random = new Random();
        double vWall = random.nextDouble()*5;
        double angle = Math.toRadians(theta);
        double vHorizantol = vProjectile*Math.cos(angle);
        double position = distance/(vHorizantol+vWall)*vHorizantol;
        return position;
    }

    public static int getScore(double gap){
        int score;
        if(gap > 3){
            System.out.format("You hit the wall below the height by %.2f. ",Math.abs(gap));
            System.out.println("not even close");
            score = -3;
            return score;
        }else if(gap <= 3 && gap >= 0){
            System.out.format("You hit the wall below the height by %.2f. ",Math.abs(gap));
            System.out.println("not quite over");
            score = -1;
            return score;
        }else if(gap < 0 && gap >= -3){
            System.out.format("You hit the wall above the height by %.2f. ",Math.abs(gap));
            System.out.println("You made it!");
            score = 4;
            return score;
        }else{
            System.out.format("You hit the wall above the height by %.2f. ",Math.abs(gap));
            System.out.println("plenty of room");
            score = 2;
            return score;
        }
    }

}

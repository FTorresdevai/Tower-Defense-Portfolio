import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Size {
    private List<Position> positions;

    public Size() {
        positions = new ArrayList<>();
    }

    public Size(Position[] positions){
        this.positions = new ArrayList<>(Arrays.asList(positions));
    }

    public void addPosition(Position p) {
        positions.add(p);
    }

    public List<Position> getPositions() {
        return positions;
    }

    public int getCount() {
        return positions.size();
    }

}


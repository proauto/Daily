package creativestudioaq.daily;

/**
 * Created by honggyu on 2016-02-11.
 */
public class checkList {
    private int _checkbutton;
    private String _checkcontent;

    public int getbutton() {
        return _checkbutton;
    }

    public String getcontent() {
        return _checkcontent;
    }

    public checkList(int button, String content) {
        _checkbutton = button;
        _checkcontent = content;
    }
}

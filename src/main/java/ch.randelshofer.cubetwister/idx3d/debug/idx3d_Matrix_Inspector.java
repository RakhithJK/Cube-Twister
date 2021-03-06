package idx3d.debug;

import idx3d.idx3d_Matrix;
import org.jhotdraw.annotation.Nonnull;

public class idx3d_Matrix_Inspector extends InspectorFrame {
    private final static long serialVersionUID = 1L;

    public idx3d_Matrix_Inspector(@Nonnull idx3d_Matrix obj, String id) {
        super(obj, id);
        addEntry(new InspectorFrameEntry(this, "float", "m00", obj.m00 + ""));
        addEntry(new InspectorFrameEntry(this, "float", "m01", obj.m01 + ""));
        addEntry(new InspectorFrameEntry(this, "float", "m02", obj.m02 + ""));
        addEntry(new InspectorFrameEntry(this, "float", "m03", obj.m03 + ""));
        addEntry(new InspectorFrameEntry(this, "float", "m10", obj.m10 + ""));
        addEntry(new InspectorFrameEntry(this, "float", "m11", obj.m11 + ""));
        addEntry(new InspectorFrameEntry(this, "float", "m12", obj.m12 + ""));
        addEntry(new InspectorFrameEntry(this, "float", "m13", obj.m13 + ""));
		addEntry(new InspectorFrameEntry(this,"float","m20",obj.m20+""));
		addEntry(new InspectorFrameEntry(this,"float","m21",obj.m21+""));
		addEntry(new InspectorFrameEntry(this,"float","m22",obj.m22+""));
		addEntry(new InspectorFrameEntry(this,"float","m23",obj.m23+""));
	}

}
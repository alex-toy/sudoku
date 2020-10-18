import java.io.File;

import javax.swing.filechooser.FileFilter;

public class PersonFileFilter extends FileFilter {

	@Override
	public boolean accept(File file) {
		String name = file.getName();
		return false;
	}

	@Override
	public String getDescription() {
		return "Person database files";
	}

}

package learntest.activelearning.plugin.handler;

import learntest.activelearning.core.settings.LearnTestResources;
import learntest.activelearning.core.settings.LearntestSettings;
import learntest.activelearning.plugin.ActiveLearntestPlugin;
import learntest.plugin.commons.PluginException;
import learntest.plugin.utils.IResourceUtils;

public class ActiveLearntestUtils {
	
	public static LearntestSettings getDefaultLearntestSettings() throws PluginException {
		LearnTestResources resources = new LearnTestResources();
		resources.setMicrobatInstrumentationJarPath(
				IResourceUtils.getResourceAbsolutePath(ActiveLearntestPlugin.PLUGIN_ID, "microbat_instrumentator.jar"));
		resources.setSavJunitRunnerJarPath(
				IResourceUtils.getResourceAbsolutePath(ActiveLearntestPlugin.PLUGIN_ID, "sav.testrunner.jar"));
		LearntestSettings learntestSettings = new LearntestSettings(resources);
		return learntestSettings;
	}
}

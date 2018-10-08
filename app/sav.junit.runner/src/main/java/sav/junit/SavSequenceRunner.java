package sav.junit;

import java.util.List;

import gentest.core.data.Sequence;
import gentest.core.data.statement.RConstructor;
import gentest.core.data.statement.Rmethod;
import gentest.core.data.statement.Statement;
import sav.sequence.execution.RuntimeExecutor;
import sav.utils.IExecutionTimer;

public class SavSequenceRunner extends SavSimpleRunner {
	private Sequence sequence;
	private RuntimeExecutor rtExecutor;
	private String curThreadName;

	public static SavSequenceRunner executeTestcases(List<Sequence> sequences, List<String> tcs, long timeout,
			IExecutionTimer timer) {
		SavSequenceRunner runner = new SavSequenceRunner();
		runner.rtExecutor = new RuntimeExecutor();
		int i = 0;
		for (Sequence sequence : sequences) {
			try {
				String tc = tcs.get(i++);
				int idx = tc.lastIndexOf(".");
				runner.className = tc.substring(0, idx);
				runner.methodName = tc.substring(idx + 1, tc.length());
				runner.sequence = sequence;
				timer.run(runner, timeout);
				System.out.println(String.format("[%s (id=%s)]: is successful? ", runner.curThreadName, runner.curThreadId, runner.successful));
				System.out.println(runner.failureMessage);
				runner.$exitTest(runner.successful + ";" + runner.failureMessage, runner.className,
						runner.methodName, runner.curThreadId);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return runner;
	}
	
	@Override
	public void run() {
		try {
			curThreadId = Thread.currentThread().getId();
			curThreadName = Thread.currentThread().getName();
			$testStarted(className, methodName);
			rtExecutor.reset();
			for (Statement stmt : sequence.getStmts()) {
				if (stmt instanceof Rmethod) {
					((Rmethod) stmt).fillMissingMethodInfo();
				}
				if (stmt instanceof RConstructor) {
					((RConstructor) stmt).fillMissingInfo();
				}
				stmt.accept(rtExecutor);
			}
		} catch (Throwable e) {
			failureMessage = e.getMessage();
		}
		$testFinished(className, methodName);
	}
}

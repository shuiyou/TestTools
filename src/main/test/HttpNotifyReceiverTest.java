import com.common.HttpNotifyReceiver;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import org.junit.Test;

/**
 * Created by hanxiaodi on 18/6/29.
 */
public class HttpNotifyReceiverTest
{
	@Before
	public void init() throws IOException
	{
		HttpNotifyReceiver.start(9001, "/", new HttpNotifyReceiver.NotifyHander() {
			@Override
			public String buildKey(Map<String, String> params) {
				return params.get("id");
			}

			@Override
			public String getResponse(Map<String, String> params) {
				return "success";
			}
		});
	}
	@After
	public void clear(){
		HttpNotifyReceiver.stop();
	}

	@Test
	public void test() throws InterruptedException {
		Map<String, String> params = new HashMap<String, String>();
		params.put("id", "007");
		params.put("a", "1");
		params.put("b", "2");
		params.put("c", "3");
//		HttpResult result = new HttpRequester(HttpNotifyReceiver.getUrl(), params).post();
//		Assert.assertEquals(200, result.getStatusCode());
//		Assert.assertEquals("success", result.getContent());
//		HttpNotifyReceiver.consumeAsserter("007").param("id", "007").param("a", "1").param("b", "2")
//				.param("c", "3").noMore();
	}

}

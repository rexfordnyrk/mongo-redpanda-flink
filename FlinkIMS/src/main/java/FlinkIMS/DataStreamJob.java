/*
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package FlinkIMS;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.apache.flink.api.common.eventtime.WatermarkStrategy;
import org.apache.flink.api.common.serialization.SimpleStringSchema;
import org.apache.flink.connector.kafka.source.enumerator.initializer.OffsetsInitializer;
import org.apache.flink.streaming.api.datastream.DataStream;
import org.apache.flink.connector.kafka.source.KafkaSource;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;
//import org.apache.flink.streaming.util.serialization.JSONKeyValueDeserializationSchema;

/**
 * Skeleton for a Flink DataStream Job.
 *
 * <p>For a tutorial how to write a Flink application, check the
 * tutorials and examples on the <a href="https://flink.apache.org">Flink Website</a>.
 *
 * <p>To package your application into a JAR file for execution, run
 * 'mvn clean package' on the command line.
 *
 * <p>If you change the name of the main class (with the public static void main(String[] args))
 * method, change the respective entry in the POM.xml file (simply search for 'mainClass').
 */
public class DataStreamJob {

	public static void main(String[] args) throws Exception {
		// Sets up the execution environment, which is the main entry point
		// to building Flink applications.
		final StreamExecutionEnvironment env = StreamExecutionEnvironment.getExecutionEnvironment();

		// Replace with your Kafka broker address(es)
		final String bootstrapServers = "127.0.0.1:19092";

		// Replace with the name of the Kafka topic for the CDC data
		final String productTopic = "e-inventory.products";

		KafkaSource<String> source = KafkaSource.<String>builder()
				.setBootstrapServers(bootstrapServers)
				.setTopics(productTopic) // Kafka or Redpanda Topic
				.setGroupId("e-ims-group") //
				.setStartingOffsets(OffsetsInitializer.earliest()) // Reading topics from first available message.
				.setValueOnlyDeserializer(new SimpleStringSchema()) // Reading the data as String
				.build();

		// Create DataStream from Kafka source
		DataStream<String> cdcStream = env.fromSource(source, WatermarkStrategy.noWatermarks(), "Kafka source");

		// Parse the JSON message using Jackson
		DataStream<Product> productStream = cdcStream
			.map(message -> {
				// Parse the JSON string
				ObjectMapper objectMapper = new ObjectMapper();
				JsonNode jsonNode = objectMapper.readTree(message);

				// Extract the "fullDocument" field as a Product object
				JsonNode fullDocumentNode = jsonNode.get("fullDocument");
				return objectMapper.treeToValue(fullDocumentNode, Product.class);
			});

		productStream.print();

		// Filter products with at least one batch expiring within 5 days
//		DataStream<Product> expiringProducts = productStream
//				.filter(product -> {
//					long today = System.currentTimeMillis();
//					for (Batch batch : product.getBatches()) {
//						long expiry = batch.getExpiryDate().getTime();
//						if (expiry - today <= 5 * 24 * 60 * 60 * 1000) { // 5 days in milliseconds
//							return true; // Stop iterating if an expiring batch is found
//						}
//					}
//					return false;
//				});

		// You can further process the expiringProducts stream (e.g., send alerts, update inventory)
//		expiringProducts.print(); // Print expiring products for demonstration


		// Execute program, beginning computation.
		env.execute("Flink Java API Skeleton");
	}
}


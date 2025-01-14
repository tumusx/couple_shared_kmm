import SwiftUI
import couple_shared

// The asynchronous request function.
func request() async throws -> String {
    do {
        // Attempt to perform the async operation and catch errors
        let fetchedData = try await CustomRequester().requester().onGetSeries()
        return fetchedData // Return fetched data
    } catch {
        print("Error: \(error)")
        throw error // Propagate the error
    }
}

struct ContentView: View {
    @State private var data: String = "Loading..." // State to hold fetched data

    var body: some View {
        VStack {
            Text(data) // Display the fetched data
        }
        .padding()
        .onAppear {
            Task {
                do {
                    let fetchedData = try await request() // Perform the async request
                    data = fetchedData // Update the state with fetched data
                } catch {
                    data = "Failed to fetch data." // Handle failure and update the state
                }
            }
        }
    }
}

struct ContentView_Previews: PreviewProvider {
    static var previews: some View {
        ContentView()
    }
}

#include <map>
#include <ctime>
#include <queue>
#include <string>
#include <vector>
#include <cstring>
#include <sstream>
#include <fstream>
#include <iostream>
#include <algorithm>
#include <unordered_map>
using namespace std;
const int maxn = 1024; // define a constant
#define ull unsigned long long
const int mod = 1e9 + 7;
struct node {
    int ti;  // URL address corresponding to the number of occurrences
    string url; // URL address
    node() {};
    node(int ti, string url): ti(ti), url(url) {};
    bool operator < (const node& _) const {  // Overload the lesser number of this structure
        if (ti == _.ti) return url > _.url; // if times equals then return the Small lexicographic order
        return ti < _.ti; // return more times
    }
};  // save the times and URL

unordered_map<string, int>hsh;   // Hash the URL address into a number
unordered_map<int, int>mp1;   // Count the number of occurrences of each address
unordered_map<int, string>mp2;   // Record the number corresponding to the returned url address

priority_queue<node>pq; // define a Maximum heap

void dealURLAddress() {
    fstream URLfile;
    URLfile.open("url.txt", ios::in|ios::out|ios::binary);   // open file in binary
    if (!URLfile.is_open()) {
        cout << "Error opening file!" << endl;
        exit (0);
    }
	else cout << "open file success!" << endl;

    char buffer[maxn];
    int id = 0; // Hash URL to number
    int f = 1, ff = 1;
    while (!URLfile.eof()) {   // take out each URL in the file for hashing
		URLfile.getline(buffer, 1024);
		string tmp(buffer);
		int tid;  // tid is the number after the URL address hash
		if (!hsh[tmp]) {
            hsh[tmp] = ++ id;
            tid = id;
		}
		else tid = hsh[tmp];
        mp1[tid]++; // add the times
        mp2[tid] = tmp; // record the URL address corresponding to this number
	}
	int tmp = hsh["http://b1.bdstatic.com/"];
	URLfile.close();
}

void countTopK(int k) {
    for (pair<int, int> tt : mp1) {
        if (tt.first == 1072) {
            cout << tt.second << endl;
        }
        pq.push(node{tt.second, mp2[tt.first]});
        node u = pq.top();
    }  // traverse the map to put the number and number of each address hash into the heap
    cout << "The number of occurrences top " << k << " address as follows: " << endl;
    while(!pq.empty() && k) {
        node u = pq.top();
        pq.pop();


        cout << "times: " << u.ti;
        cout << " URLaddress: " << ' ' << u.url << endl;
        -- k;
    }
    // output the top 100 URL address
}

void clearMemory() {
    while(!pq.empty()) pq.pop();
    hsh.clear();
    mp1.clear();
    mp2.clear();
}

int main() {
    freopen("out.txt", "w", stdout);  // Output the result to a file
    clock_t start, finish;
    start = clock();
    dealURLAddress();
    countTopK(100); // Find the top 100 times URL address
    clearMemory();
    finish = clock();
    double tottime = (double)(finish - start) / CLOCKS_PER_SEC;
//    cout << "Run times: " << tottime << "s" << endl;
    return 0;
}

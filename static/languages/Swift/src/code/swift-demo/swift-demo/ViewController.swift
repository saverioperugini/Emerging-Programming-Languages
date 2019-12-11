//
//  ViewController.swift
//  swift-demo
//
//  Created by Ajay Patnaik on 11/10/19.
//  Copyright © 2019 Ajay Patnaik. All rights reserved.
//

import UIKit
import MapKit
import WebKit

class ViewController: UIViewController, WKNavigationDelegate {

    @IBOutlet weak var firstValueTextField: UITextField!
    @IBOutlet weak var secondValueTextField: UITextField!
    @IBOutlet weak var answerLabel: UILabel!
    @IBOutlet weak var mapView: MKMapView!
    @IBOutlet weak var webView: WKWebView!
    
    let initialLocation = CLLocation(latitude: 39.7393, longitude: -84.1800)
    let regionRadius: CLLocationDistance = 1000
    
    func centerMapOnLocation(location: CLLocation) {
        let coordinateRegion = MKCoordinateRegion(center: location.coordinate, latitudinalMeters: regionRadius, longitudinalMeters: regionRadius)
        mapView.setRegion(coordinateRegion, animated: true)
    }
    
    override func viewDidLoad() {
        super.viewDidLoad()
        // Do any additional setup after loading the view.
        
        centerMapOnLocation(location: initialLocation)
        
        webView = WKWebView()
        webView.navigationDelegate = self
        view = webView
        
        let myURL = URL(string:"https://www.udayton.edu")
        let myRequest = URLRequest(url: myURL!)
        webView.load(myRequest)
    }

    @IBAction func addButton(_ sender: Any) {
        let intFirst: Int? = Int(firstValueTextField.text ?? "") ?? 0
        let intSecond: Int? = Int(secondValueTextField.text ?? "") ?? 0
        
        let ans: Int = intFirst! + intSecond!
        
        print(ans)
        
        answerLabel.text = String(ans)
        
    }
    
    @IBAction func multiplyButton(_ sender: Any) {
        let intFirst: Int? = Int(firstValueTextField.text ?? "") ?? 0
        let intSecond: Int? = Int(secondValueTextField.text ?? "") ?? 0
        
        let ans: Int = intFirst! * intSecond!
        
        print(ans)
        
        answerLabel.text = String(ans)
    }
}

